package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Conexion.ClienteConexion;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLEngineResult;
import java.util.List;
@Service
public class ClienteService implements IService<Cliente>{

    private static final ClienteConexion ClienteConexion= new ClienteConexion();

    public ClienteService() {
    }

    @Override
    public CustomResponseEntity<Cliente> Save(Cliente c){
        CustomResponseEntity<Cliente> responseValidate = ClienteConexion.Validate(c);
        if(responseValidate.getStatus()==HttpStatus.OK){
            return ClienteConexion.save(c);
        }else{
            return responseValidate;
        }
    }

    @Override
    public CustomResponseEntity<Cliente> update(Cliente cliente) {
        return ClienteConexion.update(cliente);
    }

    @Override
    public CustomResponseEntity<Cliente> delete(int id) {
        return ClienteConexion.delete(id);
    }

    @Override
    public CustomResponseEntity<Cliente> findById(int id) {
        return ClienteConexion.findById(id);
    }

    public CustomResponseEntity<List<Cliente>> findAll(){
        return ClienteConexion.findAll();
    }

    public CustomResponseEntity<List<Cliente>> findByName(String name) {
        return ClienteConexion.findByName(name);
    }
}
