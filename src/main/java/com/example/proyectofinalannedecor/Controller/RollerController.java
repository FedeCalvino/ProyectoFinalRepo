package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Service.RollerService;
import com.example.proyectofinalannedecor.Service.TelasService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Roller")
public class RollerController implements IController<Roller> {

    RollerService Rservice;

    public RollerController(RollerService rservice) {
        this.Rservice = rservice;
    }

    @Override
    public CustomResponseEntity<List<Roller>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> Save(Roller venta) {
        return null;
    }

    @PutMapping
    @Override
    public CustomResponseEntity<Roller> update(Roller roller) {
        return Rservice.update(roller);
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
