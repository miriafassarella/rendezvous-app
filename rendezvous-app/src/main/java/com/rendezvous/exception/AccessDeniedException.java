package com.rendezvous.exception;

public class AccessDeniedException extends BusinessException{
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(){
        super("Access denied");
    }
}
