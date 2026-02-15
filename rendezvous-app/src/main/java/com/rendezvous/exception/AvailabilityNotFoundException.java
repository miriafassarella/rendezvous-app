package com.rendezvous.exception;

public class AvailabilityNotFoundException extends BusinessException{

    public AvailabilityNotFoundException(String message){
        super(message);
    }

    public AvailabilityNotFoundException(){
        super("Availability not found");
    }
}
