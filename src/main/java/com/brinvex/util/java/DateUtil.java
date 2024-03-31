package com.brinvex.util.java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class DateUtil {

    public static LocalDate firstDayOfYear(int year) {
        return LocalDate.of(year, Month.JANUARY, 1);
    }

    public static LocalDate lastDayOfYear(int year) {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }

    public static LocalDate maxDate(LocalDate date1, LocalDate date2) {
        return date1.isAfter(date2) ? date1 : date2;
    }

    public static LocalDateTime maxDateTime(LocalDateTime date1, LocalDateTime date2) {
        return date1.isAfter(date2) ? date1 : date2;
    }

    public static LocalDate minDate(LocalDate date1, LocalDate date2) {
        return date1.isBefore(date2) ? date1 : date2;
    }

    public static LocalDateTime minDateTime(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.isBefore(dateTime2) ? dateTime1 : dateTime2;
    }

    public static boolean bothNullOrEqual(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        if (dateTime1 != null && dateTime2 != null) {
            return dateTime1.isEqual(dateTime2);
        }
        return dateTime1 == null && dateTime2 == null;
    }

    public static boolean bothNullOrEqual(LocalDate date1, LocalDate date2) {
        if (date1 != null && date2 != null) {
            return date1.isEqual(date2);
        }
        return date1 == null && date2 == null;
    }

    public LocalDate adjustWeekendToFriday(LocalDate day) {
        return day == null ? null : switch (day.getDayOfWeek()) {
            case SATURDAY -> day.minusDays(1);
            case SUNDAY -> day.minusDays(2);
            default -> day;
        };
    }

}
