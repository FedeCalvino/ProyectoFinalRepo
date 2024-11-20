package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulo;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Conexion.ArticuloConexion;
import com.example.proyectofinalannedecor.Conexion.RollerConexion;

import java.util.List;

public class ArticuloService implements IService<Articulo>{
    private static final ArticuloConexion articuloConexion= new ArticuloConexion();
    @Override
    public CustomResponseEntity<List<Articulo>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Articulo> Save(Articulo articulo) {
        return articuloConexion.save(articulo);
    }

    @Override
    public CustomResponseEntity<Articulo> update(Articulo venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Articulo> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Articulo> findById(int id) {
        return null;
    }
}
