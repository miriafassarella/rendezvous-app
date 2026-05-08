package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class ProviderNotFoundException extends BusinessException{

    public ProviderNotFoundException(String message){
        super(message);
    }

    public ProviderNotFoundException(){
        super("Provider not found", HttpStatus.NOT_FOUND);
    }
}
