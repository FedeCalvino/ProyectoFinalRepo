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
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> Clientes = clienteService.findAll();
        //ResponseEntity<List<Cliente>> response = new ResponseEntity<>();
        /*response.setStatus(HttpStatus.CREATED);
        response.setMessage("Se ha agregado el registro");
        response.setBody(Clientes);*/
        return null;
    }

    @PostMapping
    @Override
    public CustomResponseEntity<Cliente> Save(Cliente cliente) {
        clienteService.Save(cliente);
        CustomResponseEntity<Cliente> response = new CustomResponseEntity<>();
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("Se ha agregado el registro");
        response.setBody(cliente);
        return response;
    }

    @Override
    public CustomResponseEntity<Cliente> update(Cliente cliente) {
        return null;
    }

    @Override
    public CustomResponseEntity<Cliente> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Cliente> findById(int id) {
        return null;
    }
}
