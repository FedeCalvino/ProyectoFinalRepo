/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinalannedecor.Conexion;


import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Controller.CustomResponseEntity;
import com.sun.jdi.connect.spi.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author calvi
 */
public class VentasConexion implements IConexion<Venta>{

    @Override
    public CustomResponseEntity<Venta> save(Venta venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Venta> findById(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Venta> update(Venta venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Venta> delete(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<List<Venta>> findAll() {
        return null;
    }
}

