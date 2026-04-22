package com.rendezvous.exception;

public class RefreshTokenNotFound extends BusinessException{
    public RefreshTokenNotFound(String message) {
        super(message);
    }

    public RefreshTokenNotFound(){
        super("Refresh token not found");
    }
}
