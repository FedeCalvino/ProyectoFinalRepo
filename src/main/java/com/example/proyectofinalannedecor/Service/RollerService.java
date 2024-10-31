package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Conexion.CortinasConexion;
import com.example.proyectofinalannedecor.Conexion.VentasConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerService {

    private static final CortinasConexion CortinaConexion= new CortinasConexion();
    private static final VentasConexion VentasConexion= new VentasConexion();
    public RollerService() {

    }

    public List<Roller> Save(List<Roller> r){
        return CortinaConexion.saverRollers(r);

    }

}
