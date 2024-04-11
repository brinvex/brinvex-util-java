package com.brinvex.util.java;

import org.junit.jupiter.api.Test;

import static com.brinvex.util.java.TwoDaysDiff.between;
import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoDaysDiffTest {

    @Test
    public void twoDaysDiff() {
        assertEquals(TwoDaysDiff.EQUAL, between(now(), now()));

        assertEquals(TwoDaysDiff.ONE_DAY_AFTER, between(now().plusDays(1), now()));
        assertEquals(TwoDaysDiff.AT_LEAST_TWO_DAYS_AFTER, between(now().plusDays(2), now()));
        assertEquals(TwoDaysDiff.AT_LEAST_TWO_DAYS_AFTER, between(now().plusDays(3), now()));

        assertEquals(TwoDaysDiff.ONE_DAY_BEFORE, between(now().minusDays(1), now()));
        assertEquals(TwoDaysDiff.AT_LEAST_TWO_DAYS_BEFORE, between(now().minusDays(2), now()));
        assertEquals(TwoDaysDiff.AT_LEAST_TWO_DAYS_BEFORE, between(now().minusDays(3), now()));
    }
}
