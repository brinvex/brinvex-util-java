package com.brinvex.util.java;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParallelUtilTest {

    @Test
    public void executeAndSleep_fast() throws InterruptedException {
        Instant testStart = Instant.now();
        Duration taskMinDuration = Duration.ofSeconds(2);
        ParallelUtil.executeAndSleep(taskMinDuration, () -> {
            //no-op
        });
        Instant testEnd = Instant.now();
        Duration testDuration = Duration.between(testStart, testEnd);
        Assert.assertTrue(testDuration.compareTo(taskMinDuration) > 0);

    }

    @Test
    public void executeAndSleep_slow() throws InterruptedException {
        Instant testStart = Instant.now();
        Duration taskMinDuration = Duration.ofSeconds(2);
        Duration taskDuration = Duration.ofSeconds(3);
        ParallelUtil.executeAndSleep(taskMinDuration, () -> {
            try {
                Thread.sleep(taskDuration.toMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Instant testEnd = Instant.now();
        Duration testDuration = Duration.between(testStart, testEnd);
        Assert.assertTrue(testDuration.compareTo(taskMinDuration) > 0);
        Assert.assertTrue(testDuration.compareTo(taskDuration) > 0);

    }

    @Test
    public void executeAndSleep_interrupted() {
        Instant testStart = Instant.now();
        Duration taskMinDuration = Duration.ofSeconds(2);
        Thread t = new Thread(() -> {
            try {
                ParallelUtil.executeAndSleep(taskMinDuration, () -> {
                    throw new RuntimeException("e1");
                });
            } catch (InterruptedException e) {
                Throwable[] suppressed = e.getSuppressed();
                assertEquals("e1", suppressed[0].getMessage());
            }
        });
        t.start();
        t.interrupt();

        Instant testEnd = Instant.now();
        Duration testDuration = Duration.between(testStart, testEnd);
        Assert.assertTrue(testDuration.compareTo(taskMinDuration) < 0);

    }

}
