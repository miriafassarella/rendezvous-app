package com.rendezvous.exception;

public class ClientInUseException extends BusinessException{
    public ClientInUseException(String message) {
        super(message);
    }

    public ClientInUseException(){
        super("\"Client has appointments and cannot be deleted\"");
    }
}
