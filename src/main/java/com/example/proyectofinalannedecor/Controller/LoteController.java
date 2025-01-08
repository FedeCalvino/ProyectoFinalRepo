package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.Lote;
import com.example.proyectofinalannedecor.Service.LoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Lote")
public class LoteController implements IController<Lote> {

    LoteService Lservice;

    public LoteController(LoteService Lservice) {
        this.Lservice = Lservice;
    }


    @Override
    @GetMapping
    public CustomResponseEntity<List<Lote>> findAll() {
        return Lservice.findAll();
    }

    @GetMapping("/Fecha/{Fecha}")
    public CustomResponseEntity<List<Lote>> findAll(@PathVariable String Fecha) {
        return Lservice.findAllFecha(Fecha);
    }

    @Override
    @PostMapping
    public CustomResponseEntity<Lote> Save(@RequestBody Lote lote) {
        System.out.println(lote.getPasosordenes().get(1).getIdPasoOrden());
        return Lservice.Save(lote);
    }

    @Override
    @PutMapping
    public CustomResponseEntity<Lote> update(Lote clase) {
        return Lservice.update(clase);
    }

    @Override
    public CustomResponseEntity<Lote> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Lote> findById(int id) {
        return null;
    }
}
