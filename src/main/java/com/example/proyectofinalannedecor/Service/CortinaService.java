package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Conexion.CortinaConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CortinaService implements IService<Cortina>{
    private static final CortinaConexion cortinaConexion= new CortinaConexion();
    private static final RollerService rollerService= new RollerService();
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
        CustomResponseEntity<Cortina> response = cortinaConexion.update(cortina);
        if(cortina instanceof Roller){
            rollerService.update((Roller) cortina);
        }
        return response;
    }

    public CustomResponseEntity<Cortina> update(Cortina cortina,int idCortina) {
        CustomResponseEntity<Cortina> response = cortinaConexion.update(cortina,idCortina);
        if(cortina instanceof Roller){
            rollerService.update((Roller) cortina,idCortina);
        }
        return response;
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
