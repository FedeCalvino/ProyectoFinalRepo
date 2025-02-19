/*package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
/*
    private final com.example.proyectofinalannedecor.Servicios.AuthenticationService jwtTokenProvider;

    public JwtAuthenticationFilter(com.example.proyectofinalannedecor.Servicios.AuthenticationService jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
/*
        // No validar el token en las rutas que empiezan con "/auth"
        if (request.getRequestURI().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;  // Salir de la ejecución si la solicitud es para autenticación
        }

        String token = getJwtFromRequest(request); // Extrae el token
        if (token != null) {
            try {
                // Validar el token
                CustomResponseEntity<Claims> responseEntity = jwtTokenProvider.validarToken(token);
                if (responseEntity.getStatus().is2xxSuccessful() && responseEntity.getBody() != null) {
                    Claims claims = responseEntity.getBody();
                    String username = claims.getSubject();

                    // Configura el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(username, null, null)
                    );
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token inválido o expirado");
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido o expirado");
                return;
            }
        }

        filterChain.doFilter(request, response); // Continuar con la siguiente parte del filtros

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return (bearerToken != null && bearerToken.startsWith("Bearer ")) ? bearerToken.substring(7) : null;
    }*/
//}
