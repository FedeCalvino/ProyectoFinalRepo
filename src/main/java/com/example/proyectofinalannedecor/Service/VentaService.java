package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Conexion.ClienteConexion;
import com.example.proyectofinalannedecor.Conexion.VentasConexion;
import com.example.proyectofinalannedecor.Controller.CustomResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VentaService implements IService<Venta>{

    private static final VentasConexion VentasConexion= new VentasConexion();
    private static final ClienteConexion ClienteConexion= new ClienteConexion();

    public VentaService() {

    }

    @Override
    public CustomResponseEntity<List<Venta>> findAll() {
        return VentasConexion.findAll();
    }

    @Override
    public CustomResponseEntity<Venta> Save(Venta venta) {
        ClienteConexion.save(venta.getCliente());
        return VentasConexion.save(venta);
    }

    @Override
    public CustomResponseEntity<Venta> update(Venta venta) {
        return VentasConexion.update(venta);
    }

    @Override
    public CustomResponseEntity<Venta> delete(int id) {
        return VentasConexion.delete(id);
    }

    @Override
    public CustomResponseEntity<Venta> findById(int id) {
        return VentasConexion.findById(id);
    }

}

