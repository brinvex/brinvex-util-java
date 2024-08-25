package com.brinvex.util.java.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * The {@code Assert} class is designed for checks that enforce the correctness
 * of the application's state, which should always be valid if the code is functioning correctly.
 * It uses {@code IllegalStateException} to indicate that
 * the application's internal state is inconsistent.
 * <p>
 * The {@code Validate} class focuses on validating inputs to public methods,
 * ensuring that method arguments meet expected conditions.
 * It uses {@code IllegalArgumentException} to indicate that
 * the provided arguments are invalid for the method's operation.
 * <p>
 * Both classes, {@link Assert} and {@link Validate}, delegate all method calls to a shared
 * internal implementation. Consequently, the only difference in behavior between them
 * lies in the type of exception thrown. The logic for validation and state checking is
 * consistent across both classes, ensuring that only the exception type distinguishes them.
 */
@SuppressWarnings({"SameParameterValue", "unused"})
public class Assert {

    private static final ValidationEngine engine = new ValidationEngine(IllegalStateException::new);

    /********************************************************************
     * BOOLEAN
     ********************************************************************/

    public static void isTrue(boolean expression) {
        engine.isTrue(expression);
    }

    public static void isTrue(boolean expression, Supplier<String> msg) {
        engine.isTrue(expression, msg);
    }

    public static void isFalse(boolean expression) {
        engine.isFalse(expression);
    }

    public static void isFalse(boolean expression, Supplier<String> msg) {
        engine.isFalse(expression, msg);
    }

    /********************************************************************
     * OBJECT
     ********************************************************************/

    public static void notNull(Object input) {
        engine.notNull(input);
    }

    public static void notNull(Object input, Supplier<String> msg) {
        engine.notNull(input, msg);
    }

    public static void isNull(Object input) {
        engine.isNull(input);
    }

    public static void isNull(Object input, Supplier<String> msg) {
        engine.isNull(input, msg);
    }

    public static void equal(Object input1, Object input2) {
        engine.equal(input1, input2);
    }

    public static void equal(Object input1, Object input2, Supplier<String> msg) {
        engine.equal(input1, input2, msg);
    }

    /********************************************************************
     * STRING
     ********************************************************************/

    public static void blank(String input) {
        engine.blank(input);
    }

    public static void blank(String input, Supplier<String> msg) {
        engine.blank(input, msg);
    }

    public static void empty(String input) {
        engine.empty(input);
    }

    public static void empty(String input, Supplier<String> msg) {
        engine.empty(input, msg);
    }

    public static void contains(String haystack, String needle) {
        engine.contains(haystack, needle);
    }

    public static void contains(String haystack, String needle, Supplier<String> msg) {
        engine.contains(haystack, needle, msg);
    }

    public static void notNullNotBlank(String input) {
        engine.notNullNotBlank(input);
    }

    public static void notNullNotBlank(String input, Supplier<String> msg) {
        engine.notNullNotBlank(input, msg);
    }

    public static void nullOrNotBlank(String input) {
        engine.nullOrNotBlank(input);
    }

    public static void nullOrNotBlank(String input, Supplier<String> msg) {
        engine.nullOrNotBlank(input, msg);
    }

    /********************************************************************
     * NUMBER
     ********************************************************************/

    public static void zero(BigDecimal number) {
        engine.zero(number);
    }

    public static void zero(BigDecimal number, Supplier<String> msg) {
        engine.zero(number, msg);
    }

    public static void nullOrZero(BigDecimal number) {
        engine.nullOrZero(number);
    }

    public static void nullOrZero(BigDecimal number, Supplier<String> msg) {
        engine.nullOrZero(number, msg);
    }

    public static void positive(BigDecimal number) {
        engine.positive(number);
    }

    public static void positive(BigDecimal number, Supplier<String> msg) {
        engine.positive(number, msg);
    }

    public static void nullOrPositive(BigDecimal number) {
        engine.nullOrPositive(number);
    }

    public static void nullOrPositive(BigDecimal number, Supplier<String> msg) {
        engine.nullOrPositive(number, msg);
    }

    public static void negative(BigDecimal number) {
        engine.negative(number);
    }

    public static void negative(BigDecimal number, Supplier<String> msg) {
        engine.negative(number, msg);
    }

    public static void nullOrNegative(BigDecimal number) {
        engine.nullOrNegative(number);
    }

    public static void nullOrNegative(BigDecimal number, Supplier<String> msg) {
        engine.nullOrNegative(number, msg);
    }

    public static void equal(BigDecimal number1, BigDecimal number2) {
        engine.equal(number1, number2);
    }

    public static void equal(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        engine.equal(number1, number2, msg);
    }

    public static void greaterThan(BigDecimal number1, BigDecimal number2) {
        engine.greaterThan(number1, number2);
    }

    public static void greaterThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        engine.greaterThan(number1, number2, msg);
    }

    public static void equalOrGreaterThan(BigDecimal number1, BigDecimal number2) {
        engine.equalOrGreaterThan(number1, number2);
    }

    public static void equalOrGreaterThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        engine.equalOrGreaterThan(number1, number2, msg);
    }

    public static void lessThan(BigDecimal number1, BigDecimal number2) {
        engine.lessThan(number1, number2);
    }

    public static void lessThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        engine.lessThan(number1, number2, msg);
    }

    public static void equalOrLessThan(BigDecimal number1, BigDecimal number2) {
        engine.equalOrLessThan(number1, number2);
    }

    public static void equalOrLessThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        engine.equalOrLessThan(number1, number2, msg);
    }

    public static void opposite(BigDecimal number1, BigDecimal number2) {
        engine.opposite(number1, number2);
    }

    public static void opposite(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        engine.opposite(number1, number2, msg);
    }

    /********************************************************************
     * DATE
     ********************************************************************/

    public static void before(LocalDate leftDate, LocalDate rightDate) {
        engine.before(leftDate, rightDate);
    }

    public static void before(LocalDate leftDate, LocalDate rightDate, Supplier<String> msg) {
        engine.before(leftDate, rightDate, msg);
    }

    public static void equalOrAfter(LocalDate leftDate, LocalDate rightDate) {
        engine.equalOrAfter(leftDate, rightDate);
    }

    public static void equalOrAfter(LocalDate leftDate, LocalDate rightDate, Supplier<String> msg) {
        engine.equalOrAfter(leftDate, rightDate, msg);
    }

    /********************************************************************
     * REGEX
     ********************************************************************/

    public static void matches(String input, Pattern pattern) {
        engine.matches(input, pattern);
    }

    public static void matches(String input, Pattern pattern, Supplier<String> msg) {
        engine.matches(input, pattern, msg);
    }

    public static void emptyOrMatches(String input, Pattern pattern) {
        engine.emptyOrMatches(input, pattern);
    }

    public static void emptyOrMatches(String input, Pattern pattern, Supplier<String> msg) {
        engine.emptyOrMatches(input, pattern, msg);
    }

    public static void nullOrEmptyOrMatches(String input, Pattern pattern) {
        engine.nullOrEmptyOrMatches(input, pattern);
    }

    public static void nullOrEmptyOrMatches(String input, Pattern pattern, Supplier<String> msg) {
        engine.nullOrEmptyOrMatches(input, pattern, msg);
    }

    public static void nullOrMatches(String input, Pattern pattern) {
        engine.nullOrMatches(input, pattern);
    }

    public static void nullOrMatches(String input, Pattern pattern, Supplier<String> msg) {
        engine.nullOrMatches(input, pattern, msg);
    }
}
