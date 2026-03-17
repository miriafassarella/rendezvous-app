package com.rendezvous.exception;

public class UserNotFoundException extends BusinessException{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(){
        super("User not found");
    }
}
