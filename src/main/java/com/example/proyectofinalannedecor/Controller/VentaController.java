package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Service.VentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CustomResponseEntity<Venta> update(Venta venta) {
        CustomResponseEntity<Venta> response = ventaService.update(venta);
        return response;
    }

    @Override
    public CustomResponseEntity<Venta> delete(int id) {
        CustomResponseEntity<Venta> response = ventaService.delete(id);
        return response;
    }

    @Override
    public CustomResponseEntity<Venta> findById(int id) {
        CustomResponseEntity<Venta> response = ventaService.findById(id);
        return response;
    }

}
