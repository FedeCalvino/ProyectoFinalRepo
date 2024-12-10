package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Roller")
public class RollerController implements IController<Roller> {

    @Override
    public CustomResponseEntity<List<Roller>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> Save(Roller venta) {
        return null;
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
