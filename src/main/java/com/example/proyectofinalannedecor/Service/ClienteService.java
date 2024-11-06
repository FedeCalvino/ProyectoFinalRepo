package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Conexion.ClienteConexion;
import com.example.proyectofinalannedecor.Controller.CustomResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService{

    private static final ClienteConexion ClienteConexion= new ClienteConexion();

    public ClienteService() {
    }

    public CustomResponseEntity<Cliente> Save(Cliente c){
        return ClienteConexion.save(c);
    }

    public CustomResponseEntity<List<Cliente>> findAll(){
        return ClienteConexion.findAll();
    }

}
