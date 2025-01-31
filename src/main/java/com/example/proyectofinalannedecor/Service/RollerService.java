package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Conexion.RollerConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerService implements IService<Roller>{

    private static final RollerConexion rollerConexion= new RollerConexion();
    private static final ArticuloService articuloService= new ArticuloService();
    private static final TradicionalService tradicionalService= new TradicionalService();
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
        articuloService.update(roller);
        return rollerConexion.update(roller,IdCortina);
    }

    @Override
    public CustomResponseEntity<Roller> delete(int id) {
        return rollerConexion.delete(id);
    }

    public boolean deleteRollerIdArticulo(int id) {
        return rollerConexion.deleteFromArticulo(id);
    }

    @Override
    public CustomResponseEntity<Roller> findById(int id) {
        return null;
    }


    public void deleteFromArticulo(int idArticulo) {
        rollerConexion.deleteFromArticulo(idArticulo);
    }

    public CustomResponseEntity<Tradicional> findTradicionalArticulo(Articulo articulo) {
        CustomResponseEntity<Tradicional> response = new CustomResponseEntity<>();
        Tradicional responseTradicional =  tradicionalService.findTradicionalArticulo(articulo).getBody();
        response.setBody(responseTradicional);
        return response;
    }
}
