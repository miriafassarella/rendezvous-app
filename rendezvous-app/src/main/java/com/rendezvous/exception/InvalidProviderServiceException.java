package com.rendezvous.exception;

public class InvalidProviderServiceException extends BusinessException{

    public InvalidProviderServiceException(String message){
        super(message);
    }

    public InvalidProviderServiceException(){
        super("Service does not belong to this provider");
    }
}
