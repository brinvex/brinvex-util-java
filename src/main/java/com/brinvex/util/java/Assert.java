package com.brinvex.util.java;

import java.util.Arrays;
import java.util.function.Supplier;

public class Assert {

    public static void assertTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void assertTrue(boolean expression, Supplier<String> message) {
        if (!expression) {
            throw new IllegalStateException(message.get());
        }
    }

    public static void assertTrue(boolean expression, Object... failedObjects) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(Arrays.asList(failedObjects)));
        }
    }

    public static void assertTrue(boolean expression) {
        assertTrue(expression, "Assertion violation");
    }

}
