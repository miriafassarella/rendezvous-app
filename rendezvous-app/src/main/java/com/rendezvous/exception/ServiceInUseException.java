package com.rendezvous.exception;


public class ServiceInUseException extends BusinessException{
    public ServiceInUseException(String message) {
        super(message);
    }

    public ServiceInUseException(){
        super("It is not possible to delete a service because it is linked to one or more appointments.");
    }
}
