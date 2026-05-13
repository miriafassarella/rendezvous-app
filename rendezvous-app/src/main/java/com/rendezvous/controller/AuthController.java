package com.rendezvous.controller;

import com.rendezvous.domain.model.RefreshToken;
import com.rendezvous.domain.model.User;
import com.rendezvous.domain.service.RefreshTokenService;
import com.rendezvous.dto.loginDto.LoginRequestDto;
import com.rendezvous.dto.loginDto.LoginResponseDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthenticationManager authenticationManager;

    JwtUtil jwtUtil;

    RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          RefreshTokenService refreshTokenService){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest,
    HttpServletResponse response){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       loginRequest.getEmail(),
                       loginRequest.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtil.generateToken(user.getEmail());
        RefreshToken refreshToken = refreshTokenService.generate(user);

        Cookie cookie = new Cookie("refresh_token", refreshToken.getToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 dias em segundos
        response.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDto(accessToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {

        // 1. extrai o refresh token do cookie
        String refreshTokenValue = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refresh_token".equals(cookie.getName())) {
                    refreshTokenValue = cookie.getValue();
                }
            }
        }

        if (refreshTokenValue == null) {
            return ResponseEntity.status(401).build();
        }

        // 2. valida e gera novo access token
        RefreshToken refreshToken = refreshTokenService.validate(refreshTokenValue);
        String newAccessToken = jwtUtil.generateToken(refreshToken.getUser().getEmail());

        return ResponseEntity.ok(new LoginResponseDto(newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request,
                                       HttpServletResponse response) {
        // 1. extrai o refresh token do cookie
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("refresh_token".equals(cookie.getName())) {
                    RefreshToken refreshToken = refreshTokenService.validate(cookie.getValue());
                    refreshTokenService.revokeAllByUser(refreshToken.getUser());
                }
            }
        }

        // 2. limpa o cookie
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(0); // ← expira imediatamente
        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }
}
