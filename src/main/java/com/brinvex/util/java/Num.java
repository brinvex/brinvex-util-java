package com.brinvex.util.java;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Comparator;

import static java.math.RoundingMode.HALF_UP;

public class Num {

    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    public static final MathContext MATH_CONTEXT_P8_HALF_UP = new MathContext(8, HALF_UP);
    public static final MathContext MATH_CONTEXT_P12_HALF_UP = new MathContext(12, HALF_UP);
    public static final MathContext MATH_CONTEXT_P16_HALF_UP = new MathContext(16, HALF_UP);

    public static final BigDecimal _0$00 = new BigDecimal("0.00");
    public static final BigDecimal _0$0000 = new BigDecimal("0.0000");

    public static final BigDecimal _0$00001 = new BigDecimal("0.00001");
    public static final BigDecimal _0$0001 = new BigDecimal("0.0001");
    public static final BigDecimal _0$001 = new BigDecimal("0.001");
    public static final BigDecimal _0$005 = new BigDecimal("0.005");
    public static final BigDecimal _0$01 = new BigDecimal("0.01");
    public static final BigDecimal _0$05 = new BigDecimal("0.05");
    public static final BigDecimal _0$06 = new BigDecimal("0.06");

    public static final BigDecimal _0$10 = new BigDecimal("0.10");

    public static final BigDecimal _1$00 = new BigDecimal("1.00");

    public static final BigDecimal _1$0000 = new BigDecimal("1.0000");

    public static final BigDecimal _2$00 = new BigDecimal("2.00");

    public static final BigDecimal _3$0 = new BigDecimal("3.0");

    public static final BigDecimal _3$00 = new BigDecimal("3.00");

    public static final BigDecimal _4$00 = new BigDecimal("4.00");

    public static final BigDecimal _5$00 = new BigDecimal("5.00");

    public static final BigDecimal _6$00 = new BigDecimal("6.00");

    public static final BigDecimal _7$00 = new BigDecimal("7.00");

    public static final BigDecimal _8$00 = new BigDecimal("8.00");

    public static final BigDecimal _9$00 = new BigDecimal("9.00");

    public static final BigDecimal _100$00 = new BigDecimal("100.00");

    public static final BigDecimal _1_000_000$00 = new BigDecimal("1000000.00");

    public static final BigInteger _1_000_000 = new BigInteger("1000000");

    public static final BigInteger _2_000_000_000 = new BigInteger("2000000000");


    public static BigDecimal of(String s) {
        return s == null ? null : new BigDecimal(s);
    }

    public static boolean isNullOrZero(BigDecimal bd) {
        return bd == null || bd.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isZero(BigDecimal bd) {
        return bd.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isNullOrPositive(BigDecimal bd) {
        return bd == null || bd.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isPositive(BigDecimal bd) {
        return bd.compareTo(BigDecimal.ZERO) > 0;
    }

    public static boolean isNullOrNonNegative(BigDecimal bd) {
        return bd == null || bd.compareTo(BigDecimal.ZERO) >= 0;
    }

    public static boolean isNullOrNegative(BigDecimal bd) {
        return bd == null || bd.compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean isNegative(BigDecimal bd) {
        return bd.compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean isNullOrNonPositive(BigDecimal bd) {
        return bd == null || bd.compareTo(BigDecimal.ZERO) <= 0;
    }

    public static boolean isNonPositive(BigDecimal bd) {
        return bd.compareTo(BigDecimal.ZERO) <= 0;
    }

    public static boolean nullOrNumEqual(
            BigDecimal bd1,
            BigDecimal bd2,
            int scale,
            RoundingMode roundingMode
    ) {
        if (bd1 == null && bd2 == null) {
            return true;
        }
        if (bd1 == null || bd2 == null) {
            return false;
        }
        bd1 = bd1.setScale(scale, roundingMode);
        bd2 = bd2.setScale(scale, roundingMode);
        return bd1.compareTo(bd2) == 0;

    }

    public static boolean nullOrNumEqual(BigDecimal bd1, BigDecimal bd2, int scale) {
        return nullOrNumEqual(bd1, bd2, scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal avg(Collection<BigDecimal> decimals, int scale) {
        return avg(decimals, scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal avg(Collection<BigDecimal> decimals, int scale, RoundingMode roundingMode) {
        int size = decimals.size();
        switch (size) {
            case 0:
                return null;
            case 1:
                return decimals.iterator().next();
            default:
                return decimals
                        .stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(size), scale, roundingMode);
        }
    }

    public static BigDecimal intAvg(Collection<Integer> decimals, int scale) {
        return intAvg(decimals, scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal intAvg(Collection<Integer> decimals, int scale, RoundingMode roundingMode) {
        int size = decimals.size();
        switch (size) {
            case 0:
                return null;
            case 1:
                return BigDecimal.valueOf(decimals.iterator().next());
            default:
                return BigDecimal.valueOf(decimals
                                .stream()
                                .reduce(0, Integer::sum))
                        .divide(BigDecimal.valueOf(size), scale, roundingMode);
        }
    }

    public static BigDecimal min(Collection<BigDecimal> decimals) {
        switch (decimals.size()) {
            case 0:
                return null;
            case 1:
                return decimals.iterator().next();
            default:
                return decimals
                        .stream()
                        .min(Comparator.naturalOrder())
                        .get();
        }
    }

    public static BigDecimal max(Collection<BigDecimal> decimals) {
        switch (decimals.size()) {
            case 0:
                return null;
            case 1:
                return decimals.iterator().next();
            default:
                return decimals
                        .stream()
                        .max(Comparator.naturalOrder())
                        .get();
        }
    }

    public static BigDecimal divide(
            BigDecimal bd1,
            BigDecimal bd2,
            int scale,
            RoundingMode roundingMode
    ) {
        return bd1.divide(bd2, scale, roundingMode);
    }

    public static BigDecimal divide(
            BigDecimal bd1,
            BigDecimal bd2,
            int scale
    ) {
        return divide(bd1, bd2, scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale(BigDecimal bd, int newScale, RoundingMode roundingMode) {
        if (bd == null) {
            return null;
        }
        return bd.setScale(newScale, roundingMode);
    }

    public static BigDecimal setScale(BigDecimal bd, int newScale) {
        return setScale(bd, newScale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale0(BigDecimal bd) {
        return setScale(bd, 0, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale1(BigDecimal bd) {
        return setScale(bd, 2, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale2(BigDecimal bd) {
        return setScale(bd, 2, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale3(BigDecimal bd) {
        return setScale(bd, 3, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale4(BigDecimal bd) {
        return setScale(bd, 4, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale6(BigDecimal bd) {
        return setScale(bd, 6, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal setScale8(BigDecimal bd) {
        return setScale(bd, 8, DEFAULT_ROUNDING_MODE);
    }

    public static BigInteger toBigInteger(BigDecimal bd) {
        return bd == null ? null : bd.toBigInteger();
    }

    public static String format(Number bd, NumberFormat format) {
        return bd == null ? null : format.format(bd);
    }

    public static String format(Number bd, NumberFormat format, String nullStr) {
        return bd == null ? nullStr : format.format(bd);
    }

    public static BigDecimal distance(BigDecimal bd1, BigDecimal bd2) {
        return bd1.subtract(bd2).abs();
    }

    public static BigDecimal distance(BigDecimal bd1, BigDecimal bd2, int scale) {
        return distance(bd1, bd2, scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal distance(BigDecimal bd1, BigDecimal bd2, int scale, RoundingMode roundingMode) {
        return setScale(bd1.subtract(bd2).abs(), scale, roundingMode);
    }

    public static BigDecimal relativeChange(BigInteger oldValue, BigInteger newValue, int scale) {
        return relativeChange(new BigDecimal(oldValue), new BigDecimal(newValue), scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal relativeChange(long oldValue, long newValue, int scale) {
        return relativeChange(new BigDecimal(oldValue), new BigDecimal(newValue), scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal relativeChange(BigDecimal oldValue, BigDecimal newValue, int scale) {
        return relativeChange(oldValue, newValue, scale, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimal relativeChange(
            BigDecimal oldValue,
            BigDecimal newValue,
            int scale,
            RoundingMode roundingMode
    ) {
        return newValue.subtract(oldValue).divide(oldValue, scale, roundingMode);
    }

    public static Double round(Double number, int scale) {
        return number == null ? null : BigDecimal.valueOf(number).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static Double round2(Double number) {
        return round(number, 2);
    }

    public static BigDecimal multiplyP8(BigDecimal d1, BigDecimal d2) {
        return d1.multiply(d2, MATH_CONTEXT_P8_HALF_UP);
    }

    public static BigDecimal multiplyP12(BigDecimal d1, BigDecimal d2) {
        return d1.multiply(d2, MATH_CONTEXT_P12_HALF_UP);
    }

    public static BigDecimal multiplyP16(BigDecimal d1, BigDecimal d2) {
        return d1.multiply(d2, MATH_CONTEXT_P16_HALF_UP);
    }

}
