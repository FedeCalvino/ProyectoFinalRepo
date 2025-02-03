package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Service.OrderService;
import com.example.proyectofinalannedecor.Service.RielService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Riel")
public class RielesController implements IController<Riel> {

    RielService Rservice;

    public RielesController(RielService Rservice) {
        this.Rservice = Rservice;
    }

    @Override
    public CustomResponseEntity<List<Riel>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> Save(Riel clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> update(@RequestBody Riel riel) {
        System.out.println(riel);
        return null;
    }

    @PutMapping("/{idArt}")
    public CustomResponseEntity<Riel> updateRiel(@RequestBody Riel riel,@PathVariable int idArt) {
        riel.setIdArticulo(idArt);
        return Rservice.update(riel);
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
