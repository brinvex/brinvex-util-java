package com.brinvex.util.java;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum TwoDaysDiff {

    AT_LEAST_TWO_DAYS_BEFORE,

    ONE_DAY_BEFORE,

    EQUAL,

    ONE_DAY_AFTER,

    AT_LEAST_TWO_DAYS_AFTER;

    public static TwoDaysDiff between(LocalDate day1Incl, LocalDate day2Excl) {
        int days = Math.toIntExact(ChronoUnit.DAYS.between(day1Incl, day2Excl));
        return switch (days) {
            case -1 -> ONE_DAY_AFTER;
            case 0 -> EQUAL;
            case 1 -> ONE_DAY_BEFORE;
            default -> days > 0 ? AT_LEAST_TWO_DAYS_BEFORE : AT_LEAST_TWO_DAYS_AFTER;
        };
    }


}
