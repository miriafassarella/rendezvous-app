package com.rendezvous.exception;

public class ProviderNotAvailableException extends BusinessException{

    public ProviderNotAvailableException(String message){
        super(message);
    }

    public ProviderNotAvailableException(){
        super("The chosen time or day is not available.");
    }
}
