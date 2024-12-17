package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoTela;
import com.example.proyectofinalannedecor.Service.InstalacionService;
import com.example.proyectofinalannedecor.Service.TelasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Telas")
public class TelasController implements IController<TipoTela>{


    TelasService Tservice;

    public TelasController(TelasService Iservice) {
        this.Tservice = Iservice;
    }

    @GetMapping
    @Override
    public CustomResponseEntity<List<TipoTela>> findAll() {
        return Tservice.findAll();
    }

    @Override
    public CustomResponseEntity<TipoTela> Save(TipoTela clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<TipoTela> update(TipoTela clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<TipoTela> delete(int id) {
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public CustomResponseEntity<TipoTela> findById(@PathVariable int id) {
        return Tservice.findById(id);
    }
}
