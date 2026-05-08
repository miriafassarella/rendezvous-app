package com.rendezvous.exception;


import org.springframework.http.HttpStatus;

public class ServiceInUseException extends BusinessException{

    public ServiceInUseException(){
        super("It is not possible to delete a service because it is linked to one or more appointments.", HttpStatus.CONFLICT);
    }
}
