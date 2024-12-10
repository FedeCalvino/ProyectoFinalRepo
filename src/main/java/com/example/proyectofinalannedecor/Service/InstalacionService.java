package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Instalacion;
import com.example.proyectofinalannedecor.Conexion.CortinaConexion;
import com.example.proyectofinalannedecor.Conexion.InstalacionConexion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstalacionService implements IService<Instalacion> {
    private static final InstalacionConexion instConex= new InstalacionConexion();
    @Override
    public CustomResponseEntity<List<Instalacion>> findAll() {
        return instConex.findAll();
    }

    @Override
    public CustomResponseEntity<Instalacion> Save(Instalacion Clase) {
        return instConex.save(Clase);
    }

    @Override
    public CustomResponseEntity<Instalacion> update(Instalacion Clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<Instalacion> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Instalacion> findById(int id) {
        return null;
    }
}
