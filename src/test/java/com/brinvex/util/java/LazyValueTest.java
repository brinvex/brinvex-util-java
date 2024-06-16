package com.brinvex.util.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class LazyValueTest {

    @EnabledForJreRange(max = JRE.JAVA_20)
    @Test
    public void whenCalledMultipleTimesConcurrently_thenShouldBeCalledOnlyOnce() throws InterruptedException {
        @SuppressWarnings("unchecked") Supplier<String> mockedExpensiveFunction = Mockito.mock(Supplier.class);
        Mockito.when(mockedExpensiveFunction.get())
                .thenAnswer((Answer<?>) invocation -> {
                    Thread.sleep(1000L);
                    return "ok";
                });
        LazyValue<String> testee = new LazyValue<>(mockedExpensiveFunction);
        Mockito.verify(mockedExpensiveFunction, Mockito.never())
                .get();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.invokeAll(List.of(testee::get, testee::get));
        executorService.shutdown();
        if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

        Mockito.verify(mockedExpensiveFunction, Mockito.times(1))
                .get();
    }
}
