package com.rendezvous.exception;

import org.springframework.http.HttpStatus;

/*Abstract usado aqui para que ninguem possa usar a BusinessException diretamente*/
public abstract class BusinessException extends RuntimeException{

    private HttpStatus status;

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public BusinessException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
