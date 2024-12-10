package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Instalacion;
import com.example.proyectofinalannedecor.Service.InstalacionService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Instalacion")
public class InstalacionController implements IController<Instalacion>{
    InstalacionService Iservice;

    public InstalacionController(InstalacionService Iservice) {
        this.Iservice = Iservice;
    }
    @GetMapping
    @Override
    public CustomResponseEntity<List<Instalacion>> findAll() {
        return Iservice.findAll();
    }
    @PostMapping
    @Override
    public CustomResponseEntity<Instalacion> Save(Instalacion clase) {
        return Iservice.Save(clase);
    }

    @Override
    public CustomResponseEntity<Instalacion> update(Instalacion clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<Instalacion> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Instalacion> findById(int id) {
        return null;
    }
}
