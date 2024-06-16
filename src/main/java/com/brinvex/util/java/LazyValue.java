package com.brinvex.util.java;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * It's thread safe. If you do not need thread-safety, consider to use use {@link ThreadUnsafeLazyValue}
 * <p>
 * Inspired by <a href="https://www.baeldung.com/java-lambda-lazy-field-initialization">www.baeldung.com/java-lambda-lazy-field-initialization</a>
 */
@SuppressWarnings("JavadocLinkAsPlainText")
public final class LazyValue<T> implements Supplier<T> {

    private final Supplier<T> supplier;

    private final AtomicReference<Object> storedValueRef = new AtomicReference<>();

    private final Object nullObject = new Object();

    public LazyValue(Supplier<T> supplier) {
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
