package com.rendezvous.exception;

/*Abstract usado aqui para que ninguem possa usar a BusinessException diretamente*/
public abstract class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
