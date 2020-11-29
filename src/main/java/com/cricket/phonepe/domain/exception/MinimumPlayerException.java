package com.cricket.phonepe.domain.exception;

public class MinimumPlayerException extends RuntimeException {
    public MinimumPlayerException() {
        super("Minimum 2 Players required");
    }
}
