package com.brinvex.util.java.validation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static java.math.BigDecimal.ZERO;

/**
 * The {@code ValidationEngine} class provides the core validation logic used by the
 * {@code Assert} and {@code Validate} classes.
 * <p>
 * {@code Assert} uses this engine for invariant checks, throwing {@code IllegalStateException},
 * while {@code Validate} uses it for argument validation, throwing {@code IllegalArgumentException}.
 * <p>
 * This class is intended for internal use only by {@code Assert} and {@code Validate}.
 */
@SuppressWarnings({"SameParameterValue", "unused"})
class ValidationEngine {

    private final Function<String, RuntimeException> exceptionConstructor;

    ValidationEngine(
            Function<String, RuntimeException> exceptionConstructor
    ) {
        this.exceptionConstructor = exceptionConstructor;
    }

    private RuntimeException exception(String msg) {
        return exceptionConstructor.apply(msg);
    }

    private RuntimeException exception(Supplier<String> msg) {
        return exception(msg != null ? msg.get() : null);
    }

    private String format(Object o) {
        return (o instanceof String ? "'%s'" : "%s").formatted(o);
    }

    /********************************************************************
     * BOOLEAN
     ********************************************************************/

    void isTrue(boolean expression) {
        isTrue(expression, () -> "The validated expression is false");
    }

    void isTrue(boolean expression, Supplier<String> msg) {
        if (!expression) {
            throw exception(msg);
        }
    }

    void isFalse(boolean expression) {
        isFalse(expression, () -> "The validated expression is true");
    }

    void isFalse(boolean expression, Supplier<String> msg) {
        if (!expression) {
            throw exception(msg);
        }
    }

    /********************************************************************
     * OBJECT
     ********************************************************************/

    void notNull(Object input) {
        if (input == null) {
            throw exception("Object is null");
        }
    }

    void notNull(Object input, Supplier<String> msg) {
        if (input == null) {
            throw exception(msg);
        }
    }

    void isNull(Object input) {
        isNull(input, () -> "Object is not null: %s".formatted(format(input)));
    }

    void isNull(Object input, Supplier<String> msg) {
        if (input != null) {
            throw exception(msg);
        }
    }

    void equal(Object input1, Object input2) {
        equal(input1, input2, () -> "The object %s is not equal to the object %s, or some of them are null".formatted(format(input1), format(input2)));
    }

    void equal(Object input1, Object input2, Supplier<String> msg) {
        notNull(input1, msg);
        notNull(input2, msg);
        if (!input1.equals(input2)) {
            throw exception(msg);
        }
    }

    /********************************************************************
     * STRING
     ********************************************************************/

    void blank(String input) {
        blank(input, () -> "The string is either null or not blank: %s".formatted(format(input)));
    }

    void blank(String input, Supplier<String> msg) {
        notNull(input, msg);
        if (!input.isBlank()) {
            throw exception(msg);
        }
    }

    void empty(String input) {
        empty(input, () -> "The string is either null or not empty: %s".formatted(format(input)));
    }

    void empty(String input, Supplier<String> msg) {
        notNull(input, msg);
        if (!input.isEmpty()) {
            throw exception(msg);
        }
    }

    void contains(String haystack, String needle) {
        contains(haystack, needle, () -> "The haystack %s does not contain the needle %s, or some of them are null.".formatted(format(haystack), format(needle)));
    }

    void contains(String haystack, String needle, Supplier<String> msg) {
        notNull(haystack, msg);
        notNull(needle, msg);
        if (!haystack.contains(needle)) {
            throw exception(msg);
        }
    }

    void notNullNotBlank(String input) {
        notNullNotBlank(input, () -> "The string is either null or blank: %s".formatted(format(input)));
    }

    void notNullNotBlank(String input, Supplier<String> msg) {
        notNull(input, msg);
        if (input.isBlank()) {
            throw exception(msg);
        }
    }

    void nullOrNotBlank(String input) {
        nullOrNotBlank(input, () -> "The string is blank: %s".formatted(format(input)));
    }

    void nullOrNotBlank(String input, Supplier<String> msg) {
        if (input != null && input.isBlank()) {
            throw exception(msg);
        }
    }

    /********************************************************************
     * NUMBER
     ********************************************************************/

    void zero(BigDecimal number) {
        zero(number, () -> "The number is either null or not zero: %s".formatted(number));
    }

    void zero(BigDecimal number, Supplier<String> msg) {
        notNull(number, msg);
        if (number.compareTo(ZERO) != 0) {
            throw exception(msg);
        }
    }

    void nullOrZero(BigDecimal number) {
        nullOrZero(number, () -> "The number is not zero: %s".formatted(number));
    }

    void nullOrZero(BigDecimal number, Supplier<String> msg) {
        if (number != null && number.compareTo(ZERO) != 0) {
            throw exception(msg);
        }
    }

    void positive(BigDecimal number) {
        positive(number, () -> "The number is either null or not positive: %s".formatted(number));
    }

    void positive(BigDecimal number, Supplier<String> msg) {
        notNull(number, msg);
        if (number.compareTo(ZERO) <= 0) {
            throw exception(msg);
        }
    }

    void nullOrPositive(BigDecimal number) {
        nullOrPositive(number, () -> "The number is not positive: %s".formatted(number));
    }

    void nullOrPositive(BigDecimal number, Supplier<String> msg) {
        if (number != null && number.compareTo(ZERO) <= 0) {
            throw exception(msg);
        }
    }

    void negative(BigDecimal number) {
        negative(number, () -> "The number is either null or not negative: %s".formatted(number));
    }

    void negative(BigDecimal number, Supplier<String> msg) {
        notNull(number, msg);
        if (number.compareTo(ZERO) >= 0) {
            throw exception(msg);
        }
    }

    void nullOrNegative(BigDecimal number) {
        nullOrNegative(number, () -> "The number is not negative: %s".formatted(number));
    }

    void nullOrNegative(BigDecimal number, Supplier<String> msg) {
        if (number != null && number.compareTo(ZERO) >= 0) {
            throw exception(msg);
        }
    }

    void equal(BigDecimal number1, BigDecimal number2) {
        equal(number1, number2, () -> "The number %s is not equal to the number %s, or some of them are null".formatted(number1, number2));
    }

    void equal(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        notNull(number1, msg);
        notNull(number2, msg);
        if (number1.compareTo(number2) != 0) {
            throw exception(msg);
        }
    }

    void greaterThan(BigDecimal number1, BigDecimal number2) {
        equal(number1, number2, () -> "The number %s is not greater than the number %s, or some of them are null".formatted(number1, number2));
    }

    void greaterThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        notNull(number1, msg);
        notNull(number2, msg);
        if (number1.compareTo(number2) <= 0) {
            throw exception(msg);
        }
    }

    void equalOrGreaterThan(BigDecimal number1, BigDecimal number2) {
        equal(number1, number2, () -> "The number %s is neither equal to nor greater than %s, or some of them are null".formatted(number1, number2));
    }

    void equalOrGreaterThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        notNull(number1, msg);
        notNull(number2, msg);
        if (number1.compareTo(number2) < 0) {
            throw exception(msg);
        }
    }

    void lessThan(BigDecimal number1, BigDecimal number2) {
        equal(number1, number2, () -> "The number %s is not less than the number %s, or some of them are null".formatted(number1, number2));
    }

    void lessThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        notNull(number1, msg);
        notNull(number2, msg);
        if (number1.compareTo(number2) >= 0) {
            throw exception(msg);
        }
    }

    void equalOrLessThan(BigDecimal number1, BigDecimal number2) {
        equal(number1, number2, () -> "The number %s is neither equal to nor less than %s, or some of them are null".formatted(number1, number2));
    }

    void equalOrLessThan(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        notNull(number1, msg);
        notNull(number2, msg);
        if (number1.compareTo(number2) > 0) {
            throw exception(msg);
        }
    }

    void opposite(BigDecimal number1, BigDecimal number2) {
        opposite(number1, number2, () -> "The number %s is not opposite to the number %s, or some of them are null".formatted(number1, number2));
    }

    void opposite(BigDecimal number1, BigDecimal number2, Supplier<String> msg) {
        notNull(number1, msg);
        notNull(number2, msg);
        if (number1.compareTo(number2.negate()) != 0) {
            throw exception(msg);
        }
    }

    /********************************************************************
     * DATE
     ********************************************************************/

    void before(LocalDate leftDate, LocalDate rightDate) {
        before(leftDate, rightDate, () -> "The date %s is not before the date %s, or some of them are null".formatted(leftDate, rightDate));
    }

    void before(LocalDate leftDate, LocalDate rightDate, Supplier<String> msg) {
        notNull(leftDate, msg);
        notNull(rightDate, msg);
        if (!leftDate.isBefore(rightDate)) {
            throw exception(msg);
        }
    }

    void equalOrAfter(LocalDate leftDate, LocalDate rightDate) {
        before(leftDate, rightDate, () -> "The date %s is before the date %s, or some of them are null".formatted(leftDate, rightDate));
    }

    void equalOrAfter(LocalDate leftDate, LocalDate rightDate, Supplier<String> msg) {
        notNull(leftDate, msg);
        notNull(rightDate, msg);
        if (leftDate.isBefore(rightDate)) {
            throw exception(msg);
        }
    }

    /********************************************************************
     * REGEX
     ********************************************************************/


    void matches(String input, Pattern pattern) {
        matches(input, pattern, () -> "The string %s is either null or does not match the pattern='%s'"
                .formatted(format(input), pattern));
    }

    void matches(String input, Pattern pattern, Supplier<String> msg) {
        notNull(input, msg);
        boolean matches = pattern.matcher(input).matches();
        if (!matches) {
            throw exception(msg);
        }
    }

    void emptyOrMatches(String input, Pattern pattern) {
        emptyOrMatches(input, pattern, () -> "The string %s is either null or it is not-empty and does not match the pattern='%s'"
                .formatted(format(input), pattern));
    }

    void emptyOrMatches(String input, Pattern pattern, Supplier<String> msg) {
        notNull(input, msg);
        if (!input.isEmpty()) {
            boolean matches = pattern.matcher(input).matches();
            if (!matches) {
                throw exception(msg);
            }
        }
    }

    void nullOrEmptyOrMatches(String input, Pattern pattern) {
        emptyOrMatches(input, pattern, () -> "The string %s is not-null and not-empty and does not match the pattern='%s'"
                .formatted(format(input), pattern));
    }

    void nullOrEmptyOrMatches(String input, Pattern pattern, Supplier<String> msg) {
        if (input != null) {
            if (!input.isEmpty()) {
                boolean matches = pattern.matcher(input).matches();
                if (!matches) {
                    throw exception(msg);
                }
            }
        }
    }

    void nullOrMatches(String input, Pattern pattern) {
        nullOrMatches(input, pattern, () -> "The string %s is not-null and does not match the pattern='%s'"
                .formatted(format(input), pattern));
    }

    void nullOrMatches(String input, Pattern pattern, Supplier<String> msg) {
        if (input != null) {
            boolean matches = pattern.matcher(input).matches();
            if (!matches) {
                throw exception(msg);
            }
        }
    }
}
