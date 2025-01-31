package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Conexion.TelasConexion;
import com.example.proyectofinalannedecor.Conexion.TradicionalConexion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TradicionalService implements IService<Tradicional>{
    private static final TradicionalConexion tradicionalConexion= new TradicionalConexion();

    @Override
    public CustomResponseEntity<List<Tradicional>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Tradicional> Save(Tradicional tradi) {
        return tradicionalConexion.save(tradi);
    }

    @Override
    public CustomResponseEntity<Tradicional> update(Tradicional tradi) {
        return null;
    }

    @Override
    public CustomResponseEntity<Tradicional> delete(int id) {
        return tradicionalConexion.delete(id);
    }

    @Override
    public CustomResponseEntity<Tradicional> findById(int id) {
        return null;
    }

    public CustomResponseEntity<Tradicional> findTradicionalArticulo(Articulo articulo) {
        return tradicionalConexion.findTradicionalArticulo(articulo);
    }
}
