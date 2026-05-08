package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class UserEmailNotFoundException extends BusinessException{

    public UserEmailNotFoundException(String message) {
        super(message);
    }

    public UserEmailNotFoundException(){
        super("User email not found!", HttpStatus.NOT_FOUND);
    }
}
