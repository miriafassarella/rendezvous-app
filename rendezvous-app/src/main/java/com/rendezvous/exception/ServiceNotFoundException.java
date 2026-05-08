package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class ServiceNotFoundException extends BusinessException{

    public ServiceNotFoundException(String message){

        super(message);
    }

    public ServiceNotFoundException(){
        super("Service type not found", HttpStatus.NOT_FOUND);
    }
}
