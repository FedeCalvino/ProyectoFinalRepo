package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Service.CortinaService;
import com.example.proyectofinalannedecor.Service.TelasService;
import com.example.proyectofinalannedecor.Service.TradicionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Tradicional")
public class TradicionalController implements IController<Tradicional>{

    TradicionalService Tservice;

    public TradicionalController(TradicionalService Iservice) {
        this.Tservice = Iservice;
    }


    @Override
    public CustomResponseEntity<List<Tradicional>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Tradicional> Save(Tradicional clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<Tradicional> update(Tradicional clase) {
        return null;
    }

    @PutMapping("/{IdArticulo}/{IdCortina}")
    public CustomResponseEntity<Tradicional> updateTradicional(@RequestBody Tradicional tradi,@PathVariable int IdArticulo,@PathVariable int IdCortina) {
        tradi.setIdArticulo(IdArticulo);
        tradi.setId(IdCortina);
        return Tservice.update(tradi);
    }

    @Override
    public CustomResponseEntity<Tradicional> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Tradicional> findById(int id) {
        return null;
    }
}
