package com.rendezvous.exception;

public class InvalidProviderServiceException extends BusinessException{


    public InvalidProviderServiceException(){
        super("Service does not belong to this provider");
    }
}
