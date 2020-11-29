package com.cricket.phonepe.domain.result;

import io.vavr.control.Either;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Result {
    Either<Victory, Tie> value;

    public Result(Either<Victory, Tie> value) {
        this.value = value;
    }

    public Either<Victory, Tie> getValue() {
        return value;
    }
}
