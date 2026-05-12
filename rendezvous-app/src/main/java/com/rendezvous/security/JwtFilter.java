
package com.rendezvous.security;

import com.rendezvous.dto.exceptionError.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    private UserDetailsServiceImpl userDetailsService;

    private ObjectMapper objectMapper;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService, ObjectMapper objectMapper){

        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Pega o header Authorization
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        // 1. Extrai o token do header e  Verifica se o header existe e começa com "Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);

           try {
                email = jwtUtil.extractEmail(token);
            } catch (ExpiredJwtException e) {
                ErrorResponse error = new ErrorResponse(
                           LocalDateTime.now(),
                           HttpServletResponse.SC_UNAUTHORIZED,
                           "Unauthorized",
                           "Token expired",
                           request.getRequestURI());//path: caminho
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(error));
                return; // ← para aqui, não continua a cadeia
            } catch (JwtException e) {
               ErrorResponse error = new ErrorResponse(
                       LocalDateTime.now(),
                       HttpServletResponse.SC_UNAUTHORIZED,
                       "Unauthorized",
                       "Invalid token",
                       request.getRequestURI());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(error));
                return;
            }

        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            if (jwtUtil.isTokenValid(token, email)){
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // 5. Cria o objeto de autenticação e coloca no contexto
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // 6. Continua a cadeia de filtros
        filterChain.doFilter(request, response);

    }

}
