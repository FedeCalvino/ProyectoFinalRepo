package com.example.proyectofinalannedecor.Servicios;

import com.example.proyectofinalannedecor.Clases.Auth;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Usuario;
import com.example.proyectofinalannedecor.Conexion.UsuarioConexion;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;

import java.security.SignatureException;
import java.util.Date;

public class AuthenticationService {

    private static final String SECRET_KEY = Dotenv.load().get("JWT_SECRET");

    private final UsuarioConexion usuarioConexion;

    public AuthenticationService() {
        this.usuarioConexion = new UsuarioConexion(); // Acceso a la capa de datos
    }

    public CustomResponseEntity<String> login(Usuario usuario) {
        CustomResponseEntity<String> response = new CustomResponseEntity<>();
        if(usuario!=null) {
            Usuario autenticado = usuarioConexion.Login(usuario).getBody();
            if (autenticado != null) {
                response.setBody(generarToken(autenticado));
            } else {
                response.setMessage("El nombre o la contraseña no son correctos");
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }else{
            response.setMessage("Error al obtener el usuario");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    private String generarToken(Usuario usuario) {
        long tiempoExpiracion = 1000 * 60 * 60; // 1 hora
        return Jwts.builder()
                .setSubject(usuario.getNombre()) // Nombre del usuario
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion)) // Fecha de expiración
                .claim("userId", usuario.getId()) // Claims personalizados
                .claim("rol", usuario.getRol())
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // Firmar con la clave secreta
                .compact();
    }

    public static Claims validarToken(String token) throws Exception {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            // Validar si el token está expirado
            if (claims.getExpiration().before(new Date())) {
                throw new Exception("El token ha expirado.");
            }
            return claims;
        } catch (SignatureException e) {
            throw new Exception("Firma del token inválida.");
        } catch (Exception e) {
            throw new Exception("Token inválido: " + e.getMessage());
        }
    }

}
