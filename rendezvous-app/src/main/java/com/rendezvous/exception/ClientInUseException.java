package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class ClientInUseException extends BusinessException{

    public ClientInUseException(){
        super("\"Client has appointments and cannot be deleted\"", HttpStatus.CONFLICT);
    }
}
