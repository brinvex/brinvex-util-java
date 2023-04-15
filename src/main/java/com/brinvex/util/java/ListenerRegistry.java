package com.brinvex.util.java;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ListenerRegistry<T> {

    public interface Registration extends Serializable {
        void remove();
    }

    private final Map<Registration, Consumer<T>> listeners = new LinkedHashMap<>();

    public Registration addListener(Consumer<T> listener) {
        Registration registration = new Registration() {
            @Override
            public void remove() {
                synchronized (ListenerRegistry.this) {
                    listeners.remove(this);
                }
            }
        };
        synchronized (this) {
            listeners.put(registration, listener);
        }
        return registration;
    }

    public void notifyListeners(T event) {
        for (Consumer<T> listener : listeners.values()) {
            listener.accept(event);
        }
    }
}
