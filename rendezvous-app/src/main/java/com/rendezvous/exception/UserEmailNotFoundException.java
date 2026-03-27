package com.rendezvous.exception;

public class UserEmailNotFoundException extends BusinessException{

    public UserEmailNotFoundException(String message) {
        super(message);
    }

    public UserEmailNotFoundException(){
        super("User email not found!");
    }
}
