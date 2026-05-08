package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

public class AdminNotFoundException extends BusinessException{

    public AdminNotFoundException(){
        super("Admin not found", HttpStatus.NOT_FOUND);
    }
}
