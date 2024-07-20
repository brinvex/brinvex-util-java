package com.brinvex.util.java;

public final class Holder<VALUE> {

    private VALUE value;

    public void value(VALUE value) {
        this.value = value;
    }

    public VALUE value() {
        return value;
    }

    @Override
    public String toString() {
        return "Holder[" + value + ']';
    }

}
