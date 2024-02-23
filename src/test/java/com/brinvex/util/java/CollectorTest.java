package com.brinvex.util.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CollectorTest {

    @Test
    public void toMapCollector_orderNotGuaranteed() {
        Map<Integer, Integer> m1 = IntStream.range(0, 100000)
                .boxed()
                .collect(java.util.stream.Collectors.toMap(identity(), identity()));
        assertEquals(HashMap.class, m1.getClass());

        TreeMap<Integer, Integer> tm1 = new TreeMap<>(m1);
        assertNotEquals(m1.toString(), tm1.toString());
    }

    @Test
    public void toMapCollector_duplicateNotAllowed() {
        record Pair(int x, int y) {
        }
        ;
        List<Pair> pairs = List.of(new Pair(1, 10), new Pair(2, 20), new Pair(1, 11));
        try {
            Map<Integer, Integer> m1 = pairs.stream().collect(java.util.stream.Collectors.toMap(Pair::x, Pair::y));
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Duplicate key 1 (attempted merging values 10 and 11)", e.getMessage());
        }
    }

    @Test
    public void toLinkedMapWithoutCustomCollector_duplicateNotAllowed() {
        record Pair(int x, int y) {
        }
        List<Pair> pairs = List.of(new Pair(1, 10), new Pair(2, 20), new Pair(1, 11));
        try {
            Map<Integer, Integer> m1 = pairs.stream().collect(java.util.stream.Collectors.toMap(
                    Pair::x,
                    Pair::y,
                    (u, v) -> {
                        throw new IllegalStateException("Duplicate key for values %s and %s".formatted(u, v));
                    },
                    LinkedHashMap::new));
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Duplicate key for values 10 and 11", e.getMessage());
        }
    }

    @Test
    public void toLinkedMapCollector_ok() {
        record Pair(int x, int y) {
        }
        List<Pair> pairs = IntStream.range(1, 100000).boxed().map(i -> new Pair(i, -i)).toList();

        Map<Integer, Integer> m1 = pairs.stream().collect(com.brinvex.util.java.Collectors.toLinkedMap(Pair::x, Pair::y));
        assertEquals(m1.keySet(), pairs.stream().map(Pair::x).collect(java.util.stream.Collectors.toSet()));
        assertEquals(new ArrayList<>(m1.values()), pairs.stream().map(Pair::y).toList());
    }

    @Test
    public void toLinkedMapCollector_duplicateNotAllowed() {
        record Pair(int x, int y) {
        }
        List<Pair> pairs = List.of(new Pair(1, 10), new Pair(2, 20), new Pair(1, 11));
        try {
            Map<Integer, Integer> m1 = pairs.stream().collect(com.brinvex.util.java.Collectors.toLinkedMap(Pair::x, Pair::y));
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Duplicate key 1 (attempted merging values 10 and 11)", e.getMessage());
        }
    }
}
