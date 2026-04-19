package com.rendezvous.exception;

public class AdminNotFoundException extends BusinessException{


    public AdminNotFoundException(String message) {
        super(message);
    }

    public AdminNotFoundException(){
        super("Admin not found");
    }
}
