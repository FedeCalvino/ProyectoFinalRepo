package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Clases.Orden.PasoOrden;
import com.example.proyectofinalannedecor.Service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Orden")
public class OrderController implements IController<Orden>{

    OrderService Oservice;

    public OrderController(OrderService Oservice) {
        this.Oservice = Oservice;
    }

    @Override
    @GetMapping
    public CustomResponseEntity<List<Orden>> findAll() {

        CustomResponseEntity<List<Orden>> ordenes = Oservice.findAll();
        List<Orden> ordenesList = ordenes.getBody();
        List<Orden> updatedOrdenesList = new ArrayList<>();

        for (Orden orden : ordenesList) {
            Orden updatedOrden = Oservice.findAllPasos(orden).getBody();
            updatedOrdenesList.add(updatedOrden);
        }
        ordenes.setBody(updatedOrdenesList);
        return ordenes;

    }

    @Override
    public CustomResponseEntity<Orden> Save(Orden clase) {
        return null;
    }

    @PutMapping("/PasoOrden/{PasoNombre}")
    public CustomResponseEntity<Orden> AvanzarPasoOrden(
            @PathVariable String PasoNombre,
            @RequestBody Orden orden) {
        CustomResponseEntity<Orden> response = new CustomResponseEntity<>();
        response.setBody(Oservice.avanzarPaso(PasoNombre, orden));
        return response;
    }

    @Override
    @PutMapping
    public CustomResponseEntity<Orden> update(Orden order) {
        return Oservice.update(order);
    }

    @Override
    public CustomResponseEntity<Orden> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Orden> findById(int id) {
        return Oservice.findById(id);
    }

}
