package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Usuario;
import com.example.proyectofinalannedecor.Service.UsuarioService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class AuthenticationService {
    @Value("${JWT_SECRET}")
    private String secretKey;

    private Key jwtKey;
    private final UsuarioService usuarioService;


    public AuthenticationService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    public CustomResponseEntity<String> login(Usuario usuario) {
        CustomResponseEntity<String> response = new CustomResponseEntity<>();

        if (usuario == null || usuario.getNombre() == null || usuario.getPassword() == null) {
            response.setMessage("Nombre de usuario y contraseña son obligatorios.");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }

        Usuario autenticado = usuarioService.Login(usuario).getBody();

        if (autenticado != null) {
            String token = generarToken(autenticado);
            System.out.println("Token generado: " + token);
            response.setBody(token);
            response.setMessage("Autenticación exitosa.");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("El nombre de usuario o la contraseña son incorrectos.");
            response.setStatus(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }

    public String generarToken(Usuario usuario) {
        long tiempoExpiracion = 1000 * 60 * 60; // 1 hora

        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))
                .claim("nombre", usuario.getNombre())
                .signWith(jwtKey)
                .compact();
    }
/*
    @PostConstruct
    public void init() {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET no está definido en el archivo de configuración.");
        }
        this.jwtKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public CustomResponseEntity<String> login(Usuario usuario) {
        CustomResponseEntity<String> response = new CustomResponseEntity<>();

        if (usuario == null || usuario.getNombre() == null || usuario.getPassword() == null) {
            response.setMessage("Nombre de usuario y contraseña son obligatorios.");
            response.setStatus(HttpStatus.BAD_REQUEST);
            return response;
        }

        Usuario autenticado = usuarioService.Login(usuario).getBody();

        if (autenticado != null) {
            String token = generarToken(autenticado);
            System.out.println("Token generado: " + token);
            response.setBody(token);
            response.setMessage("Autenticación exitosa.");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setMessage("El nombre de usuario o la contraseña son incorrectos.");
            response.setStatus(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }

    public Claims obtenerClaims(String token) throws Exception {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new Exception("El token ha expirado.");
        } catch (JwtException e) {
            throw new Exception("Token inválido: " + e.getMessage());
        }
    }

    public CustomResponseEntity<Claims> validarToken(String token) {
        System.out.println("Validando el token: " + token);
        CustomResponseEntity<Claims> response = new CustomResponseEntity<>();
        try {
            Claims claims = obtenerClaims(token);
            response.setBody(claims);
            response.setMessage("Token válido.");
            response.setStatus(HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            System.out.println("Error: El token ha expirado.");
            response.setMessage("El token ha expirado.");
            response.setStatus(HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            System.out.println("Error: Token mal formado.");
            response.setMessage("Token mal formado.");
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (SignatureException e) {
            System.out.println("Error: Firma del token no válida.");
            response.setMessage("Firma del token no válida.");
            response.setStatus(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            System.out.println("Error desconocido: " + e.getMessage());
            response.setMessage("Error desconocido en la validación del token.");
            response.setStatus(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }
*/
}
