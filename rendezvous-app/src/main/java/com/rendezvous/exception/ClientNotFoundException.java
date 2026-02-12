package com.rendezvous.exception;

public class ClientNotFoundException extends BusinessException{

    public ClientNotFoundException(String message){
        super(message);
    }

    public ClientNotFoundException(){
        super("Client not found");
    }
}
