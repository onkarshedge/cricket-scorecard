package com.cricket.phonepe.domain;

public class MinimumPlayerException extends RuntimeException {
    public MinimumPlayerException() {
        super("Minimum 2 Players required");
    }
}
