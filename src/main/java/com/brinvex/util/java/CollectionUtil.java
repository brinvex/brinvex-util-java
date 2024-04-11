package com.brinvex.util.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
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

    public static <K extends Comparable<? super K>, V> TreeMap<K, V> treeMap(
            K key1,
            V value1
    ) {
        TreeMap<K, V> map = new TreeMap<>();
        map.put(key1, value1);
        return map;
    }

    public static <E> Set<E> asSet(Collection<E> collection) {
        return collection == null ? null : collection instanceof Set<?> ? (Set<E>) collection : new HashSet<>(collection);
    }

    public static <E> LinkedHashSet<E> asLinkedSet(Collection<E> collection) {
        return collection == null ? null : collection instanceof LinkedHashSet<?> ? (LinkedHashSet<E>) collection : new LinkedHashSet<>(collection);
    }

    public static <E> Set<E> asSetWithoutExcluded(Collection<E> collection, Collection<E> excluded) {
        if (collection == null) {
            return null;
        }
        Set<E> set = new HashSet<>(collection);
        if (excluded != null) {
            set.removeAll(excluded);
        }
        return set;
    }

    public static <E> List<E> asList(Collection<E> collection) {
        return collection == null ? null : collection instanceof List<?> ? (List<E>) collection : new ArrayList<>(collection);
    }

    public static <E> E getFirstThrowIfMore(Collection<E> collection) {
        int size = collection.size();
        return switch (size) {
            case 0 -> null;
            case 1 -> collection.iterator().next();
            default -> throw new IllegalStateException(
                    format("Expecting empty or one-element collection but got #%s, %s", size, collection));
        };
    }

    public static <E> E getLast(List<E> elements) {
        int size = elements.size();
        return size == 0 ? null : elements.get(size - 1);
    }

    public static <E> E getLast(List<E> elements, Supplier<E> def) {
        int size = elements.size();
        return size == 0 ? def.get() : elements.get(size - 1);
    }

    public static <K, V extends Comparable<? super V>> LinkedHashMap<K, V> sortMapByValues(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V> LinkedHashMap<K, V> sortMapByValues(
            Map<K, V> map, Comparator<? super V> cmp
    ) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(cmp));
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @Deprecated
    public static <K, V> LinkedHashMap<K, V> copyToLinkedMap(Map<K, V> sourceMap, Consumer<Map<K, V>> copyConsumer) {
        LinkedHashMap<K, V> copiedMap;
        if (sourceMap == null) {
            copiedMap = null;
        } else {
            copiedMap = new LinkedHashMap<>(sourceMap);
        }
        copyConsumer.accept(copiedMap);
        return copiedMap;
    }

    public static <E> List<E> append(List<E> list, E element) {
        list.add(element);
        return list;
    }

    public static <E> List<E> joinToList(Collection<E> cole1, Collection<E> cole2) {
        List<E> result = new ArrayList<>(cole1);
        result.addAll(cole2);
        return result;
    }

    public static <E> LinkedHashSet<E> joinToLinkedSet(Collection<E> cole1, Collection<E> cole2) {
        LinkedHashSet<E> result = new LinkedHashSet<>(cole1);
        result.addAll(cole2);
        return result;
    }

    public static <E> HashSet<E> intersectionAsHashSet(Collection<E> cole1, Collection<E> cole2) {
        HashSet<E> result = new HashSet<>(cole1);
        result.retainAll(cole2);
        return result;
    }

    public static <E> LinkedHashSet<E> intersectionAsLinkedSet(Collection<E> cole1, Collection<E> cole2) {
        LinkedHashSet<E> result = new LinkedHashSet<>(cole1);
        result.retainAll(cole2);
        return result;
    }
}
