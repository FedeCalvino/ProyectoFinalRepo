/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinalannedecor.Conexion;


import com.example.proyectofinalannedecor.Clases.*;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
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



public class VentasConexion implements IConexion<Venta>{
    private static final String SQL_INSERT = "INSERT INTO VENTA(CLIENTE_ID, FECHA, OBRA, FECHA_INSTALACION) VALUES( ?, ?, ?,?)";;
    private static final String SQL_DELETE = "DELETE FROM VENTA WHERE ID_VENTA = ?";
    private static final String SQL_UPDATE = "UPDATE VENTA SET FECHA = ? , CLIENTE_ID = ?,OBRA=?,FECHA_INSTALACION=?  WHERE ID_VENTA = ?";
    private static final String SQL_SELECT_ALL="SELECT * FROM venta v JOIN CLIENTE c on c.ID_CLIENTE=v.CLIENTE_ID";
    private static final String SQL_INSERT_ARTICULO_VENTA = "INSERT INTO VENTA_ARTICULO (ID_ARTICULO,ID_VENTA) VALUES (?,?)";

    public static Connection conexion;

    @Override
    public CustomResponseEntity<Venta> save(Venta v) {
        java.sql.Connection conexion=null;
        System.out.println(v.getFechaInstalacion());
        CustomResponseEntity<Venta> response = new CustomResponseEntity<>();
        if(v.getCliente()==null){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setBody(v);
            response.setMessage("La venta no tiene cliente");
            return response;
        }
        if (v.getListaArticulos().isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST);
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
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(v.getId()==null){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setBody(v);
            response.setMessage("La venta no tiene id");
        }else{
            response.setStatus(HttpStatus.OK);
            response.setBody(v);
            response.setMessage("Venta creada con exito");
        }
        return response;
    }

    public boolean SaveArticuloVenta(int ArticuloId,int VentaId) {
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_ARTICULO_VENTA, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ArticuloId);
            ps.setInt(2, VentaId);
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
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
                new Cliente(rs.getInt("CLIENTE_ID"),bgdecimal,"",bgdecimal,"","",""),
                new ArrayList<Articulo>(),
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
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
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
        CustomResponseEntity<Venta> response = new CustomResponseEntity<>();
        java.sql.Connection conexion=null;
        try{
            Venta v = this.findById(id).getBody();
            if(v!=null) {
                conexion = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_DELETE);
                ps.setInt(1, id);
                ps.execute();
                response.setBody(v);
                response.setStatus(HttpStatus.OK);
                response.setMessage("Venta eliminada");
            }else{
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("Venta no encontrada");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<List<Venta>> findAll() {
        CustomResponseEntity<List<Venta>> response = new CustomResponseEntity<>();
        List<Venta> ventas=new ArrayList<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Venta v = new Venta(rs.getInt(1),
                        new Cliente(rs.getInt(7),rs.getBigDecimal(8),rs.getString(9),rs.getBigDecimal(10),rs.getString(11),rs.getString(12),rs.getString(13))
                        ,null,rs.getDate(3),rs.getDate(5),rs.getInt(4),rs.getString(6));
                ventas.add(v);
            }
            response.setBody(ventas);
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }finally{
            try{
                connection.close();
            }catch(Exception e){

            }
        }
        if(ventas.isEmpty()){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("no se encontraron ventas");
        }else{
            response.setStatus(HttpStatus.OK);
            response.setBody(ventas);
        }
        return response;
    }

}

