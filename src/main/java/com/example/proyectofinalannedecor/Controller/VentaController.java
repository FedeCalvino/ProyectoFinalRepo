package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Service.VentaService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @GetMapping("/Mensaje")
    public String MandaMensaje() {
        return ventaService.MandarMensaje("msj");
    }


    @PutMapping("/UpdateFO/{instalacion}/{obra}/{idVen}")
    public CustomResponseEntity<String> update(@PathVariable String instalacion, @PathVariable String obra, @PathVariable int idVen) {
        CustomResponseEntity<String> response = ventaService.updateVentaFO(instalacion,obra,idVen);
        return response;
    }
    @GetMapping("/Wordenes")
    public CustomResponseEntity<List<Venta>> findAllConOrden() {
        CustomResponseEntity<List<Venta>> response = ventaService.findAllWordenes();
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
    public CustomResponseEntity<Venta> delete(@PathVariable int id) {
        System.out.println(id);
        CustomResponseEntity<Venta> response = ventaService.delete(id);
        return response;
    }

    @Override
    @GetMapping("/{id}")
    public CustomResponseEntity<Venta> findById(@PathVariable int id) {
        System.out.println(id);
        CustomResponseEntity<Venta> response = ventaService.findById(id);
        return response;
    }

}
