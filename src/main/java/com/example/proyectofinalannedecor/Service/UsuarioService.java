package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Usuario;
import com.example.proyectofinalannedecor.Conexion.RollerConexion;
import com.example.proyectofinalannedecor.Conexion.UsuarioConexion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService implements IService<Usuario> {
    private static final UsuarioConexion usuarioConexion= new UsuarioConexion();

    public CustomResponseEntity<Usuario> Login(Usuario usuario) {
        return usuarioConexion.Login(usuario);
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
    public CustomResponseEntity<Usuario> update(Usuario venta) {
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
