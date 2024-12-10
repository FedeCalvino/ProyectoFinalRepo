package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Usuario;
import com.example.proyectofinalannedecor.Service.UsuarioService;
import com.example.proyectofinalannedecor.Service.VentaService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Usuario")

public class UsuarioController implements IController<Usuario>{

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public CustomResponseEntity<List<Usuario>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Usuario> Save(Usuario venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Usuario> update(@RequestBody Usuario venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Usuario> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Usuario> findById(int id) {
        return null;
    }
}
