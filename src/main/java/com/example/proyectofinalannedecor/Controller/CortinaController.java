package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Service.ClienteService;
import com.example.proyectofinalannedecor.Service.CortinaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Cortina")

public class CortinaController implements IController<Cortina> {

    private CortinaService CortinaService;

    public CortinaController(CortinaService cs) {
        this.CortinaService = cs;
    }

    @Override
    public CustomResponseEntity<List<Cortina>> findAll() {
        return null;
    }


    @Override
    public CustomResponseEntity<Cortina> Save(Cortina venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Cortina> update(Cortina venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Cortina> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Cortina> findById(int id) {
        return null;
    }
}
