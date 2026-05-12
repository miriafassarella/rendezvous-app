package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends BusinessException{

    public AccessDeniedException(){
        super("Access denied");
    }
}
