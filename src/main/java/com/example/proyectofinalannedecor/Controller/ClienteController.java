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
    public CustomResponseEntity<Cliente> Save(Cliente cliente) {
        CustomResponseEntity<Cliente> response = clienteService.Save(cliente);
        return response;
    }

    @Override
    public CustomResponseEntity<Cliente> update(Cliente cliente) {
        CustomResponseEntity<Cliente> response = clienteService.update(cliente);
        return response;
    }

    @Override
    public CustomResponseEntity<Cliente> delete(int id) {
        CustomResponseEntity<Cliente> response = clienteService.delete(id);
        return response;
    }

    @Override
    public CustomResponseEntity<Cliente> findById(int id) {
        CustomResponseEntity<Cliente> response = clienteService.findById(id);
        return response;
    }
}
