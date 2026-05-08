package com.rendezvous.exception;

public class RefreshTokenExpired extends BusinessException{

    public RefreshTokenExpired(){
        super("Refresh token expired or revoked.");
    }
}
