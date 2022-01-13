package com.hda.dev.exceptions;

public class UserWithEmailNotFound extends RuntimeException{

    public UserWithEmailNotFound(String message) {
        super(message);
    }
}
