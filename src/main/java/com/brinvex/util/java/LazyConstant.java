package com.brinvex.util.java;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * Inspired by <a href="https://www.baeldung.com/java-lambda-lazy-field-initialization">www.baeldung.com/java-lambda-lazy-field-initialization</a>
 */
public interface LazyConstant<T> extends Supplier<T> {

    static <T> LazyConstant<T> threadSafe(Supplier<T> supplier) {
        return new ThreadSafeLazyConstant<>(supplier);
    }

    static <T> LazyConstant<T> nonThreadSafe(Supplier<T> supplier) {
        return new NonThreadSafeLazyConstant<>(supplier);
    }

    final class ThreadSafeLazyConstant<T> implements LazyConstant<T> {

        private final Supplier<T> supplier;

        private final AtomicReference<Object> storedValueRef = new AtomicReference<>();

        private static final Object nullSubstitute = new Object();

        public ThreadSafeLazyConstant(Supplier<T> supplier) {
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
                        storedValueRef.set(value == null ? nullSubstitute : value);
                        return value;
                    } else {
                        if (storedValue == nullSubstitute) {
                            return null;
                        }
                        @SuppressWarnings({"ReassignedVariable", "unchecked"})
                        T typedStoredValue = (T) storedValue;
                        return typedStoredValue;
                    }
                }
            } else {
                if (storedValue == nullSubstitute) {
                    return null;
                }
                @SuppressWarnings({"ReassignedVariable", "unchecked"})
                T typedStoredValue = (T) storedValue;
                return typedStoredValue;
            }
        }
    }

    final class NonThreadSafeLazyConstant<T> implements LazyConstant<T> {

        private final Supplier<T> supplier;

        private Object storedValue = null;

        private static final Object nullSubstitute = new Object();

        public NonThreadSafeLazyConstant(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get() {
            if (storedValue == null) {
                T value = supplier.get();
                storedValue = value == null ? nullSubstitute : value;
                return value;
            } else {
                if (storedValue == nullSubstitute) {
                    return null;
                }
                @SuppressWarnings({"unchecked"})
                T typedStoredValue = (T) storedValue;
                return typedStoredValue;
            }
        }
    }

}
