package com.solarsmart.frontoffice.models.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
    }
}
