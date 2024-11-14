package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Service.ClienteService;
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
    @DeleteMapping("/{id}")
    @Override
    public CustomResponseEntity<Cliente> delete(@PathVariable int id) {
        CustomResponseEntity<Cliente> response = clienteService.delete(id);
        return response;
    }
    @GetMapping("/{id}")
    @Override
    public CustomResponseEntity<Cliente> findById(@PathVariable int id) {
        CustomResponseEntity<Cliente> response = clienteService.findById(id);
        return response;
    }
    @GetMapping("Name/{name}")
    public CustomResponseEntity<List<Cliente>> findByName(@PathVariable String name){
        CustomResponseEntity<List<Cliente>> response = clienteService.findByName(name);
        return response;
    }
}
