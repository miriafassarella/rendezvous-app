package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class ClientNotFoundException extends BusinessException{

    public ClientNotFoundException(String message){
        super(message);
    }

    public ClientNotFoundException(){
        super("Client not found", HttpStatus.NOT_FOUND);
    }
}
