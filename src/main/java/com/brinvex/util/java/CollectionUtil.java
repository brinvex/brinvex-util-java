package com.brinvex.util.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.lang.String.format;

@SuppressWarnings("DuplicatedCode")
public class CollectionUtil {

    public static <K, V> LinkedHashMap<K, V> linkedMap(K key, V value) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        map.put(key, value);
        return map;
    }

    public static <K, V> LinkedHashMap<K, V> linkedMap(K key1, V value1, K key2, V value2) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }

    public static <K, V> LinkedHashMap<K, V> linkedMap(K key1, V value1, K key2, V value2, K key3, V value3) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        return map;
    }

    public static <K, V> LinkedHashMap<K, V> linkedMap(
            K key1,
            V value1,
            K key2,
            V value2,
            K key3,
            V value3,
            K key4,
            V value4
    ) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        return map;
    }

    public static <K, V> LinkedHashMap<K, V> linkedMap(
            K key1,
            V value1,
            K key2,
            V value2,
            K key3,
            V value3,
            K key4,
            V value4,
            K key5,
            V value5
    ) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        return map;
    }

    public static <K, V> LinkedHashMap<K, V> linkedMap(
            K key1,
            V value1,
            K key2,
            V value2,
            K key3,
            V value3,
            K key4,
            V value4,
            K key5,
            V value5,
            K key6,
            V value6
    ) {
        LinkedHashMap<K, V> map = new LinkedHashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        return map;
    }

    public static <E> Set<E> asSet(Collection<E> collection) {
        return collection == null ? null : collection instanceof Set<?> ? (Set<E>) collection : new HashSet<>(collection);
    }

    public static <E> List<E> asList(Collection<E> collection) {
        return collection == null ? null : collection instanceof List<?> ? (List<E>) collection : new ArrayList<>(collection);
    }

    public static <E> E getFirstThrowIfMore(Collection<E> collection) {
        int size = collection.size();
        switch (size) {
            case 0:
                return null;
            case 1:
                return collection.iterator().next();
            default:
                throw new IllegalStateException(
                        format("Expecting empty or one-element collection but got #%s, %s", size, collection));
        }
    }

    public static <E> E getLast(List<E> elements) {
        int size = elements.size();
        return size == 0 ? null : elements.get(size - 1);
    }

    public static <E> E getLast(List<E> elements, Supplier<E> def) {
        int size = elements.size();
        return size == 0 ? def.get() : elements.get(size - 1);
    }
}
