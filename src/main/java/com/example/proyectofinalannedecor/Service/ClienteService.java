package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Conexion.ClienteConexion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteService{

    private static final ClienteConexion ClienteConexion= new ClienteConexion();

    public ClienteService() {
    }

    public Cliente Save(Cliente c){
        return ClienteConexion.save(c);
    }

    public List<Cliente> findAll(){
        return ClienteConexion.findAll();
    }

}
