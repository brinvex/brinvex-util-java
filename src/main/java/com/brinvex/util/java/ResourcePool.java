package com.brinvex.util.java;

import java.io.Closeable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class ResourcePool<RESOURCE> implements AutoCloseable {

    private final BlockingQueue<Integer> resourceIndexQueue;

    private final Map<Integer, ResourceReadiness> resources;

    private final BiFunction<Integer, RESOURCE, RESOURCE> resourceSupplier;

    private final long pollTimeoutInMillis;

    private class ResourceReadiness {
        private boolean ready = false;
        private RESOURCE resource;
    }

    public interface ResourceTask<RESULT, RESOURCE_KEY, RESOURCE> {
        RESULT execute(RESOURCE_KEY resourceKey, RESOURCE resource, Consumer<Boolean> restartNotifier);
    }

    public ResourcePool(
            int resourcesCount,
            Duration pollTimeout,
            BiFunction<Integer, RESOURCE, RESOURCE> resourceSupplier
    ) {
        this.pollTimeoutInMillis = pollTimeout.toMillis();

        this.resourceSupplier = resourceSupplier;

        this.resourceIndexQueue = resourcesCount == 0 ? null : new LinkedBlockingQueue<>(resourcesCount);
        this.resources = new ConcurrentHashMap<>(resourcesCount);
        for (int resourceIndex = 0; resourceIndex < resourcesCount; resourceIndex++) {
            this.resourceIndexQueue.add(resourceIndex);
            this.resources.put(resourceIndex, new ResourceReadiness());
        }
    }

    public void initAllResources() throws TimeoutException, InterruptedException {
        for (int i = 0; i < resources.keySet().size(); i++) {
            execute((integer, resource, restartNotifier) -> null);
        }
    }

    public <RESULT> RESULT execute(ResourceTask<RESULT, Integer, RESOURCE> task) throws TimeoutException, InterruptedException {
        Integer resourceIndex = null;
        try {
            if (pollTimeoutInMillis >= 0) {
                resourceIndex = resourceIndexQueue.poll(pollTimeoutInMillis, TimeUnit.MILLISECONDS);
            } else {
                resourceIndex = resourceIndexQueue.take();
            }
            if (resourceIndex == null) {
                throw new TimeoutException(String.format("Timeout while borrowing resource - pollTimeoutInMillis=%s, thread=%s, task=%s",
                        pollTimeoutInMillis, Thread.currentThread().getName(), task));
            }
            ResourceReadiness resourceReadiness = resources.get(resourceIndex);
            if (!resourceReadiness.ready) {
                resourceReadiness.resource = resourceSupplier.apply(resourceIndex, resourceReadiness.resource);
                requireNonNull(resourceReadiness.resource);
                resourceReadiness.ready = true;
            }
            boolean[] restart = new boolean[]{false};
            try {
                return task.execute(
                        resourceIndex,
                        resourceReadiness.resource,
                        restartNotifier -> restart[0] = restartNotifier
                );
            } finally {
                if (restart[0]) {
                    resourceReadiness.ready = false;
                }
            }
        } finally {
            if (resourceIndex != null) {
                resourceIndexQueue.add(resourceIndex);
            }
        }
    }

    @Override
    public void close() throws Exception {
        List<Exception> closeExceptions = new ArrayList<>();
        for (ResourceReadiness resourceReadiness : resources.values()) {
            RESOURCE resource = resourceReadiness.resource;
            if (resource instanceof Closeable) {
                try {
                    ((Closeable) resource).close();
                } catch (Exception exception) {
                    closeExceptions.add(exception);
                }
            }
        }
        if (!closeExceptions.isEmpty()) {
            throw ExceptionUtil.getLastExceptionAndSuppressOthers(closeExceptions);
        }
    }
}
