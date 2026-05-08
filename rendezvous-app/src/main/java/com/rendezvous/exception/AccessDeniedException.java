package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends BusinessException{
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(){
        super("Access denied");
    }
}
