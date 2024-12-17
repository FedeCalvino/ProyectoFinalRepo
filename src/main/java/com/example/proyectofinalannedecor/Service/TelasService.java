package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoTela;
import com.example.proyectofinalannedecor.Conexion.TelasConexion;
import com.example.proyectofinalannedecor.Conexion.UsuarioConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelasService implements IService<TipoTela>{

    private static final TelasConexion telaConexion= new TelasConexion();

    @Override
    public CustomResponseEntity<List<TipoTela>> findAll() {
        return telaConexion.findAll();
    }

    @Override
    public CustomResponseEntity<TipoTela> Save(TipoTela Clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<TipoTela> update(TipoTela Clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<TipoTela> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<TipoTela> findById(int id) {
        return telaConexion.findById(id);
    }
}
