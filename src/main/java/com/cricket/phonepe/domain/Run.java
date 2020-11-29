package com.cricket.phonepe.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Run {
    public static final Run ZERO = new Run(0);
    public static final Run SINGLE = new Run(1);
    public static final Run DOUBLE = new Run(2);
    public static final Run THREE = new Run(3);
    public static final Run FOUR = new Run(4);
    public static final Run SIX = new Run(6);


    private Run(int value) {
        this.value = value;
    }

    private int value;

    public Run add(Run other) {
        return new Run(this.value + other.value);
    }

    public int getValue() {
        return value;
    }

    public boolean shouldChangeStrike() {
        return value % 2 == 1;
    }

    public boolean isGreaterThanOrEqualTo(Run other) {
        return value >= other.value;
    }
}
