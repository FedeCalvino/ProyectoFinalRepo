package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Conexion.CortinasConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CortinaService {

    private static final CortinasConexion CortinasConexion = new CortinasConexion();

    public CortinaService() {

    }

    public Cortina Save(Cortina r){
        return CortinasConexion.saveCortina(r);

    }

    public List<Roller> findAll(){return null;}
}
