package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.Bastones;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Soporte;
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
    public CustomResponseEntity<Riel> findRielArticulo(Articulo riel) {
        System.out.println(riel);
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        Riel responseRiel =  rielConexion.findRielArticulo(riel).getBody();
        System.out.println(riel);
        response.setBody(responseRiel);
        return response;

    }

    @Override
    public CustomResponseEntity<Riel> Save(Riel riel) {
        return rielConexion.save(riel);
    }

    @Override
    public CustomResponseEntity<Riel> update(Riel riel) {
        rielConexion.updateBastones(riel);
        rielConexion.updateSoportes(riel);
        return rielConexion.update(riel);
    }

    @Override
    public CustomResponseEntity<Riel> delete(int id) {
        return rielConexion.delete(id);
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
