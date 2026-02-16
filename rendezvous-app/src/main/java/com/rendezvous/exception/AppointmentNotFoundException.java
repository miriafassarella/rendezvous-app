package com.rendezvous.exception;

public class AppointmentNotFoundException extends BusinessException{
    public AppointmentNotFoundException(String message) {
        super(message);
    }

    public AppointmentNotFoundException() {
        super("Appointment not found");
    }
}
