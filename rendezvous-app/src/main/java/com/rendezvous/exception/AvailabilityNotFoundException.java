package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class AvailabilityNotFoundException extends BusinessException{

    public AvailabilityNotFoundException(){

        super("Availability not found", HttpStatus.NOT_FOUND);
    }
}
