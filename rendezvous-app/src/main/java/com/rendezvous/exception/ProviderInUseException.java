package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class ProviderInUseException extends BusinessException{
    public ProviderInUseException(String message) {
        super(message);
    }

    public ProviderInUseException(){
        super("\"Provider has appointments and cannot be deleted\"", HttpStatus.CONFLICT);
    }
}
