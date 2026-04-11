package com.rendezvous.exception;


public class ServiceExceptionInUse extends BusinessException{
    public ServiceExceptionInUse(String message) {
        super(message);
    }

    public ServiceExceptionInUse(){
        super("It is not possible to delete a service because it is linked to one or more appointments.");
    }
}
