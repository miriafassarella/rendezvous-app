package com.rendezvous.exception;

public class AvailabilityFoundException extends BusinessException{
    public AvailabilityFoundException(String message) {
        super(message);
    }

    public AvailabilityFoundException(){
        super("Availability already registered for this provider.");
    }
}
