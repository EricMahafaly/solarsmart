package com.solarsmart.frontoffice.models.exception;

public class AuthenticationException extends Exception{

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
