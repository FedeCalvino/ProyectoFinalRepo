package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

@Controller
public class RielesController implements IController<Riel> {

    @Override
    public CustomResponseEntity<List<Riel>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> Save(Riel clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> update(Riel clase) {
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
}
