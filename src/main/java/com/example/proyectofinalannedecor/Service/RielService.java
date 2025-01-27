package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.Bastones;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Soporte;
import com.example.proyectofinalannedecor.Conexion.OrdenConexion;
import com.example.proyectofinalannedecor.Conexion.RielConexion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RielService implements IService<Riel>{


    private static final RielConexion rielConexion= new RielConexion();

    @Override
    public CustomResponseEntity<List<Riel>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> Save(Riel riel) {
        return rielConexion.save(riel);
    }

    @Override
    public CustomResponseEntity<Riel> update(Riel Clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> findById(int id) {
        return null;
    }

    public CustomResponseEntity<Bastones> SaveBaston(Bastones baston) {
        return rielConexion.saveBaston(baston);
    }

    public CustomResponseEntity<Soporte> SaveSoporte(Soporte soportes) {
        return rielConexion.saveSoportes(soportes);
    }
}
