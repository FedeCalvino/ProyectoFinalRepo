package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Conexion.ClienteConexion;
import com.example.proyectofinalannedecor.Conexion.CortinaConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CortinaService implements IService<Cortina>{
    private static final CortinaConexion cortinaConexion= new CortinaConexion();

    @Override
    public CustomResponseEntity<List<Cortina>> findAll() {
        return cortinaConexion.findAll();
    }

    @Override
    public CustomResponseEntity<Cortina> Save(Cortina cortina) {
        return cortinaConexion.save(cortina);
    }

    @Override
    public CustomResponseEntity<Cortina> update(Cortina cortina) {
        return cortinaConexion.update(cortina);
    }

    @Override
    public CustomResponseEntity<Cortina> delete(int id) {
        return cortinaConexion.delete(id);
    }

    @Override
    public CustomResponseEntity<Cortina> findById(int id) {
        return cortinaConexion.findById(id);
    }
}
