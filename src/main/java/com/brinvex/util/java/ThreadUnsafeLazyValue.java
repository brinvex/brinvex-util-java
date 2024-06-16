package com.brinvex.util.java;

import java.util.function.Supplier;

/**
 * It's not thread safe. If you need thread-safety, use {@link LazyValue}
 * <p>
 * Inspired by <a href="https://www.baeldung.com/java-lambda-lazy-field-initialization">www.baeldung.com/java-lambda-lazy-field-initialization</a>
 */
public final class ThreadUnsafeLazyValue<T> implements Supplier<T> {

    private final Supplier<T> supplier;

    private Object storedValue = null;

    private final Object nullObject = new Object();

    public ThreadUnsafeLazyValue(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (storedValue == null) {
            T value = supplier.get();
            storedValue = value == null ? nullObject : value;
            return value;
        } else {
            if (storedValue == nullObject) {
                return null;
            }
            @SuppressWarnings({"unchecked"})
            T typedStoredValue = (T) storedValue;
            return typedStoredValue;
        }
    }

}
