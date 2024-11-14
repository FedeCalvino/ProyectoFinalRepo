package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Service.VentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Ventas")
public class VentaController implements IController<Venta> {

    private final VentaService ventaService;

    public VentaController(VentaService vs) {
        this.ventaService = vs;
    }

    @GetMapping()
    @Override
    public CustomResponseEntity<List<Venta>> findAll() {
        CustomResponseEntity<List<Venta>> response = ventaService.findAll();
        return response;
    }


    @PostMapping()
    @Override
    public CustomResponseEntity<Venta> Save(@RequestBody Venta venta) {
        CustomResponseEntity<Venta> response = ventaService.Save(venta);
        return response;
    }

    @Override
    @PutMapping()
    public CustomResponseEntity<Venta> update(@RequestBody Venta venta) {
        CustomResponseEntity<Venta> response = ventaService.update(venta);
        return response;
    }

    @Override
    @DeleteMapping("/{id}")
    public CustomResponseEntity<Venta> delete(int id) {
        CustomResponseEntity<Venta> response = ventaService.delete(id);
        return response;
    }

    @Override
    @GetMapping("/{id}")
    public CustomResponseEntity<Venta> findById(int id) {
        CustomResponseEntity<Venta> response = ventaService.findById(id);
        return response;
    }

}
