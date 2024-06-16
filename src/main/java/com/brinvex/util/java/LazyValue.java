package com.brinvex.util.java;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * Inspired by <a href="https://www.baeldung.com/java-lambda-lazy-field-initialization">www.baeldung.com/java-lambda-lazy-field-initialization</a>
 */
public interface LazyValue<T> extends Supplier<T> {

    static <T> LazyValue<T> threadSafe(Supplier<T> supplier) {
        return new ThreadSafeLazyValue<>(supplier);
    }

    static <T> LazyValue<T> nonThreadSafe(Supplier<T> supplier) {
        return new NonThreadSafeLazyValue<>(supplier);
    }

    final class ThreadSafeLazyValue<T> implements LazyValue<T> {

        private final Supplier<T> supplier;

        private final AtomicReference<Object> storedValueRef = new AtomicReference<>();

        private final Object nullObject = new Object();

        public ThreadSafeLazyValue(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get() {
            Object storedValue = storedValueRef.get();
            if (storedValue == null) {
                synchronized (storedValueRef) {
                    storedValue = storedValueRef.get();
                    if (storedValue == null) {
                        T value = supplier.get();
                        storedValueRef.set(value == null ? nullObject : value);
                        return value;
                    } else {
                        if (storedValue == nullObject) {
                            return null;
                        }
                        @SuppressWarnings({"ReassignedVariable", "unchecked"})
                        T typedStoredValue = (T) storedValue;
                        return typedStoredValue;
                    }
                }
            } else {
                if (storedValue == nullObject) {
                    return null;
                }
                @SuppressWarnings({"ReassignedVariable", "unchecked"})
                T typedStoredValue = (T) storedValue;
                return typedStoredValue;
            }
        }
    }

    final class NonThreadSafeLazyValue<T> implements LazyValue<T> {

        private final Supplier<T> supplier;

        private Object storedValue = null;

        private final Object nullObject = new Object();

        public NonThreadSafeLazyValue(Supplier<T> supplier) {
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

}
