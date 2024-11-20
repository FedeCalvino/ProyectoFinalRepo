package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Conexion.CortinaConexion;
import com.example.proyectofinalannedecor.Conexion.RollerConexion;
import com.example.proyectofinalannedecor.Conexion.VentasConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerService implements IService<Roller>{

    private static final RollerConexion rollerConexion= new RollerConexion();
    @Override
    public CustomResponseEntity<List<Roller>> findAll() {
        return rollerConexion.findAll();
    }

    @Override
    public CustomResponseEntity<Roller> Save(Roller venta) {
        return rollerConexion.save(venta);
    }

    @Override
    public CustomResponseEntity<Roller> update(Roller venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> findById(int id) {
        return null;
    }
}
