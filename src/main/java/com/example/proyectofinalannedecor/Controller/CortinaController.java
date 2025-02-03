package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Service.CortinaService;
import org.springframework.web.bind.annotation.*;

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
    public CustomResponseEntity<Cortina> Save(Cortina cor) {
        return null;
    }

    @PutMapping()
    @Override
    public CustomResponseEntity<Cortina> update(Cortina cor) {
        return CortinaService.update(cor);
    }

    @PutMapping("/{idCortina}")
    public CustomResponseEntity<Cortina> update(@RequestBody Cortina cor,@PathVariable int idCortina) {
        cor.setId(idCortina);
        return CortinaService.update(cor);
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
