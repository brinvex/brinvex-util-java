package com.brinvex.util.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectionUtilTest {

    @Test
    public void removeAdjacentDuplicatesFromTreeMap() {
        TreeMap<Integer, Integer> m1 = new TreeMap<>(Map.of(1, 11, 2, 22, 3, 33, 33, 33, 44, 44));
        boolean modified = CollectionUtil.removeAdjacentDuplicates(m1.entrySet(), (e1, e2) -> e1.getValue().equals(e2.getValue()));
        assertTrue(modified);

        TreeMap<Integer, Integer> m2 = new TreeMap<>(Map.of(1, 11, 2, 22, 3, 33, 44, 44));

        assertEquals(m2, m1);
    }

    @Test
    public void removeAdjacentDuplicatesFromEmptyCollection() {
        CollectionUtil.removeAdjacentDuplicates(emptyList());
        CollectionUtil.removeAdjacentDuplicates(emptyMap().entrySet());
        CollectionUtil.removeAdjacentDuplicates(emptySet());
    }

    @Test
    public void removeAdjacentDuplicatesFromSingletonCollection() {
        {
            List<Integer> c1 = List.of(1);
            CollectionUtil.removeAdjacentDuplicates(c1);
            assertEquals(List.of(1), c1);
        }
        {
            Set<Integer> c1 = Set.of(1);
            CollectionUtil.removeAdjacentDuplicates(c1);
            assertEquals(Set.of(1), c1);
        }
        {
            Map<Integer, Integer> c1 = Map.of(1, 2);
            CollectionUtil.removeAdjacentDuplicates(c1.entrySet());
            assertEquals(Map.of(1, 2), c1);
        }
    }

    @Test
    public void removeAdjacentDuplicatesFromList() {
        List<Integer> m1 = new ArrayList<>(List.of(1, 11, 2, 22, 3, 33, 33, 33, 44, 44));
        boolean modified = CollectionUtil.removeAdjacentDuplicates(m1);
        assertTrue(modified);

        List<Integer> m2 = new ArrayList<>(List.of(1, 11, 2, 22, 3, 33, 44));

        assertEquals(m2, m1);
    }
}
