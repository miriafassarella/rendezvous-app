package com.rendezvous.exception;

public class AvailabilityInUseException extends BusinessException{
    public AvailabilityInUseException(String message) {
        super(message);
    }

    public AvailabilityInUseException(){
        super("\"It is not possible to exclude this availability date because the provider has one or more appointments on this date.\"");
    }
}
