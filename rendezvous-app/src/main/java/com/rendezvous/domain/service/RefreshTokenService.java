package com.rendezvous.domain.service;

import com.rendezvous.domain.model.RefreshToken;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.repository.RefreshTokenRepository;
import com.rendezvous.exception.RefreshTokenExpired;
import com.rendezvous.exception.RefreshTokenNotFound;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwt.refresh.expiration.days:7}")
    private int refreshExpirationDays;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken generate(User user){
        revokeAllByUser(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(refreshExpirationDays));
        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken validate(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new RefreshTokenNotFound());

        if (!refreshToken.isValid()){
            throw new RefreshTokenExpired("Refresh token expired or revoked.");
        }
        return refreshToken;
    }

    public void revokeAllByUser(User user) {
        List<RefreshToken> tokens = refreshTokenRepository.findAllByUser_Id(user.getId());
        tokens.forEach(token -> token.setRevoked(true));
        refreshTokenRepository.saveAll(tokens);
    }

}
