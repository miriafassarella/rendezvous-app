package com.rendezvous.exception;

public class ProviderNotFoundException extends BusinessException{

    public ProviderNotFoundException(String message){
        super(message);
    }

    public ProviderNotFoundException(){
        super("Provider not found");
    }
}
