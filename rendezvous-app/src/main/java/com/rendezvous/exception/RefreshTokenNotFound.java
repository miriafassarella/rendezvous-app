package com.rendezvous.exception;

public class RefreshTokenNotFound extends BusinessException{

    public RefreshTokenNotFound(){
        super("Refresh token not found");
    }
}
