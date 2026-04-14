package com.rendezvous.exception;

public class ProviderInUseException extends BusinessException{
    public ProviderInUseException(String message) {
        super(message);
    }

    public ProviderInUseException(){
        super("\"It is not possible to delete a provider because it is linked to one or more appointments.\"");
    }
}
