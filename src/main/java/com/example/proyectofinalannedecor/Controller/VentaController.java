package com.example.proyectofinalannedecor.Controller;

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
    public ResponseEntity<List<Venta>> findAll() {
        List<Venta> ventas = ventaService.findAll();
        if (ventas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }


    @PostMapping()
    @Override
    public CustomResponseEntity<Venta> Save(@RequestBody Venta venta) {

        if (venta.getCliente() == null) {
            return CustomResponseEntity.badRequest("El cliente está vacío");
        }

        if (venta.getListaCortinas().isEmpty()) {
            CustomResponseEntity response = CustomResponseEntity.badRequest("No se agregó ninguna cortina");
            response.setBody(venta);
            return response;
        }
        return CustomResponseEntity.ok("Se ha agregado el registro", venta);
        /*ventaService.save(venta);
        CustomResponseEntity<Venta> response = new CustomResponseEntity<>();
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("Se ha agregado el registro");
        response.setBody(venta);
        return response;*/
    }

    @Override
    public CustomResponseEntity<Venta> update(Venta venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Venta> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Venta> findById(int id) {
        return null;
    }

}
