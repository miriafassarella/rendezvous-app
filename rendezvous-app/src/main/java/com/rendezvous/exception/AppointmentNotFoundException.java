package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class AppointmentNotFoundException extends BusinessException{

    public AppointmentNotFoundException() {

        super("Appointment not found", HttpStatus.NOT_FOUND);
    }
}
