package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Conexion.RollerConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerService implements IService<Roller>{

    private static final RollerConexion rollerConexion= new RollerConexion();
    private static final ConfigService configService= new ConfigService();

    @Override
    public CustomResponseEntity<List<Roller>> findAll() {
        return rollerConexion.findAll();
    }

    @Override
    public CustomResponseEntity<Roller> Save(Roller venta) {
        return rollerConexion.save(venta);
    }
    public CustomResponseEntity<Roller> findRollerArticulo(Articulo articulo) {
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();
        Roller roll =  rollerConexion.findRollerArticulo(articulo).getBody();
        response.setBody(roll);
        return response;
    }

    @Override
    public CustomResponseEntity<Roller> update(Roller roller) {

        return rollerConexion.update(roller);
    }
    public CustomResponseEntity<Roller> update(Roller roller,int IdCortina) {

        return rollerConexion.update(roller,IdCortina);
    }

    @Override
    public CustomResponseEntity<Roller> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> findById(int id) {
        return null;
    }

    public CustomResponseEntity<Riel> findRielArticulo(Articulo riel) {
        System.out.println(riel);
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        Riel responseRiel =  rollerConexion.findRielArticulo(riel).getBody();
        System.out.println(riel);
        response.setBody(responseRiel);
        return response;

    }
}
