package com.example.proyectofinalannedecor.Controladores;

import com.example.proyectofinalannedecor.Clases.Auth;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Usuario;
import com.example.proyectofinalannedecor.Servicios.AuthenticationService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/auth")
public class AuthController{

    private final AuthenticationService authService;

    public AuthController() {
        this.authService = new AuthenticationService();
    }

    @PostMapping("/login")
    public CustomResponseEntity<String> login(@RequestBody Usuario usuario) {
        return authService.login(usuario);
    }
}
