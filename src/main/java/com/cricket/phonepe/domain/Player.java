package com.cricket.phonepe.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

}

