package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Cliente")

public class ClienteController implements IController<Cliente>{
    private ClienteService clienteService;

    public ClienteController(ClienteService cs) {
        this.clienteService = cs;
    }


    @GetMapping
    @Override
    public CustomResponseEntity<List<Cliente>> findAll() {
        CustomResponseEntity<List<Cliente>> response = clienteService.findAll();
        return response;
    }

    @PostMapping
    @Override
    public CustomResponseEntity<Cliente> Save(@RequestBody Cliente cliente) {
        CustomResponseEntity<Cliente> response = clienteService.Save(cliente);
        return response;
    }

    @PutMapping
    @Override
    public CustomResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
        CustomResponseEntity<Cliente> response = clienteService.update(cliente);
        return response;
    }
    @DeleteMapping
    @Override
    public CustomResponseEntity<Cliente> delete(@PathVariable int id) {
        CustomResponseEntity<Cliente> response = clienteService.delete(id);
        return response;
    }
    @GetMapping("/Id")
    @Override
    public CustomResponseEntity<Cliente> findById(@PathVariable int id) {
        CustomResponseEntity<Cliente> response = clienteService.findById(id);
        return response;
    }
}
