package com.brinvex.util.java;

import java.util.function.Function;

public class LambdaUtil {

    public static <I, T, O> Function<I, O> chain(Function<I, T> fnc1, Function<T, O> fnc2) {
        return i -> fnc2.apply(fnc1.apply(i));
    }

}
