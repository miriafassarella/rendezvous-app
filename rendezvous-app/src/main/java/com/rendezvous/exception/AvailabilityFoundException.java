package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class AvailabilityFoundException extends BusinessException{

    public AvailabilityFoundException(){
        super("Availability already registered for this provider.", HttpStatus.FOUND);
    }
}
