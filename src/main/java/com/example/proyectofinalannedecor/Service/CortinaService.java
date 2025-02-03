package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Conexion.ArticuloConexion;
import com.example.proyectofinalannedecor.Conexion.CortinaConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CortinaService implements IService<Cortina>{
    private static final CortinaConexion cortinaConexion= new CortinaConexion();
    private static final RollerService rollerService= new RollerService();
    private static final ArticuloConexion articuloConexion= new ArticuloConexion();
    private static final TradicionalService tradicionalService= new TradicionalService();

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
        CustomResponseEntity<Articulo> Articuloresponse = articuloConexion.update(cortina);
        CustomResponseEntity<Cortina> response = cortinaConexion.update(cortina);
        if(cortina instanceof Roller){
            rollerService.update((Roller) cortina);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Cortina> delete(int id) {
        return cortinaConexion.delete(id);
    }

    public boolean deleteFromArticulo(int id) {
        return cortinaConexion.deleteFromArticulo(id);
    }

    @Override
    public CustomResponseEntity<Cortina> findById(int id) {
        return cortinaConexion.findById(id);
    }

}
