/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinalannedecor.Conexion;


import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Controller.CustomResponseEntity;
import com.sun.jdi.connect.spi.Connection;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author calvi
 */
public class VentasConexion implements IConexion<Venta>{
    private static final String SQL_INSERT = "INSERT INTO VENTA(CLIENTE_ID, FECHA, OBRA, FECHA_INSTALACION) VALUES(?, ?, ?, ?)";;
    private static final String SQL_DELETE = "DELETE FROM VENTA WHERE ID_VENTA = ?";
    private static final String SQL_UPDATE = "UPDATE VENTA SET FECHA = ? , CLIENTE_ID = ?,OBRA=?,FECHA_INSTALACION=?  WHERE ID_VENTA = ?";


    public static Connection conexion;

    @Override
    public CustomResponseEntity<Venta> save(Venta v) {
        java.sql.Connection conexion=null;
        CustomResponseEntity<Venta> response = new CustomResponseEntity<>();
        if(v.getCliente()==null){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setBody(v);
            response.setMessage("La venta no tiene cliente");
            return response;
        }
        if (v.getListaCortinas().isEmpty()) {
            response.setMessage("No se agregó ninguna cortina");
            response.setBody(v);
            return response;
        }
        try{
            System.out.println(v.getObra());
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, v.getCliente().getId());
            Date fecha = Date.valueOf(LocalDate.now());
            ps.setDate(2, fecha);;
            ps.setString(3, v.getObra());
            ps.setDate(4, v.getFechaInstalacion());
            ps.execute();
            v.setFecha(fecha);

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                v.setId(rs.getInt(1));
            }
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Venta creada con exito");
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Error al crear la venta");
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return response;
    }


    @Override
    public CustomResponseEntity<Venta> findById(Integer id) {
        CustomResponseEntity<Venta> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;

        try {
            conexion = (java.sql.Connection) Conexion.GetConexion();
            String SQL_FIND_BY_ID = "SELECT * FROM VENTA WHERE ID_VENTA = ?";
            PreparedStatement ps = conexion.prepareStatement(SQL_FIND_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            BigDecimal bgdecimal = BigDecimal.valueOf(0);
            if (rs.next()) {

                Venta venta = new Venta(
                rs.getInt("ID_VENTA"),
                new Cliente(rs.getInt("CLIENTE_ID"),bgdecimal,"",bgdecimal,"",""),
                new ArrayList<Cortina>(),
                rs.getDate("FECHA"),
                        rs.getDate("FECHA_INSTALACION"),
                rs.getInt("PRECIO"),
                rs.getString("OBRA")
                );

                response.setBody(venta);
                response.setStatus(HttpStatus.OK);
                response.setMessage("Venta encontrada");
            } else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("Venta no encontrada");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Error al buscar la venta");
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    @Override
    public CustomResponseEntity<Venta> update(Venta v) {
        java.sql.Connection conexion=null;
        CustomResponseEntity<Venta> response = new CustomResponseEntity<>();
        try{
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setDate(1, v.getFecha());
            ps.setInt(2,v.getCliente().getId());
            ps.setString(3,v.getObra());
            ps.setDate(4, v.getFechaInstalacion());
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return response;
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

