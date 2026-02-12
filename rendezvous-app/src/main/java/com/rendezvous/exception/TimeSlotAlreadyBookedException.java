package com.rendezvous.exception;

public class TimeSlotAlreadyBookedException extends BusinessException{

    public TimeSlotAlreadyBookedException(String message){
        super(message);
    }

    public TimeSlotAlreadyBookedException() {
        super("Time slot already booked");
    }
}
