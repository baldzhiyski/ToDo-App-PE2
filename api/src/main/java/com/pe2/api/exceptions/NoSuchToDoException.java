package com.pe2.api.exceptions;

public class NoSuchToDoException extends RuntimeException {
    public NoSuchToDoException(String message) {
        super(message);
    }
}
