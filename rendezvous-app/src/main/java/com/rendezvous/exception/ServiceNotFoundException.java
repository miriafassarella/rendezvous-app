package com.rendezvous.exception;

public class ServiceNotFoundException extends BusinessException{

    public ServiceNotFoundException(String message){
        super(message);
    }

    public ServiceNotFoundException(){
        super("Service type not found");
    }
}
