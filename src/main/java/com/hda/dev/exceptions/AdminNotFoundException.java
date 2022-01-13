package com.hda.dev.exceptions;

public class AdminNotFoundException extends RuntimeException{
    public AdminNotFoundException(String message) {
        super(message);
    }
}
