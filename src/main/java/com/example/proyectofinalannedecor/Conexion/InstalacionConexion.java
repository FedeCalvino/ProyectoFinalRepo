package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Instalacion;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InstalacionConexion implements IConexion<Instalacion>{
    private static final String SQL_DELETE = "DELETE FROM INSTALACION WHERE ID_INSTALACION = ?";
    private static final String SQL_INSERT_INSTALACION = "INSERT INTO INSTALACION(ID_VENTA,COMIENZO,FINAL,ACLARACIONES,TITULO) VALUES (?,?,?,?,?)";
    private static final String SQL_GET_INSTALACION = "select * from INSTALACION";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM INSTALACION WHERE ID_INSTALACION = ?";
    private static final String SQL_SELECT_BY_VENTA = "SELECT * FROM INSTALACION WHERE ID_VENTA = ?";
    private static final String SQL_UPDATE_FECHA = "UPDATE INSTALACION SET COMIENZO=?,FINAL=? WHERE ID_VENTA = ?";

    @Override
    public CustomResponseEntity<Instalacion> save(Instalacion instalacion) {
        java.sql.Connection conexion=null;
        System.out.println(instalacion.toString());
        CustomResponseEntity<Instalacion> response = new CustomResponseEntity<>();

        try{

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 1);

        conexion = (java.sql.Connection) Conexion.GetConexion();
        PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_INSTALACION, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,instalacion.getIdVenta());
        ps.setString(2,instalacion.getStart());
        ps.setString(3,instalacion.getEnd());
        ps.setString(4,instalacion.getAclaraciones());
        ps.setString(5,instalacion.getTitle());
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();

        while(rs.next()){
            instalacion.setId(rs.getInt(1));
        }
            response.setBody(instalacion);
        response.setMessage("OK");
        response.setStatus(HttpStatus.OK);
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
    public CustomResponseEntity<Instalacion> findByIdVenta(Integer id) {
        CustomResponseEntity<Instalacion> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_VENTA);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Instalacion i = new Instalacion(
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getString(4),
                        rs.getString(6)
                );
                i.setId(rs.getInt(1));
                response.setBody(i);
                response.setMessage("OK");
            }else{
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("No se encontro el registro");
                response.setBody(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Instalacion> findById(Integer id) {
        CustomResponseEntity<Instalacion> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Instalacion i = new Instalacion(
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getString(4),
                        rs.getString(6)
                );
                i.setId(rs.getInt(1));
                response.setBody(i);
                response.setMessage("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Instalacion> update(Instalacion instalacion) {
        return null;
    }

    @Override
    public CustomResponseEntity<Instalacion> delete(Integer id) {
        CustomResponseEntity<Instalacion> response = new CustomResponseEntity<>();
        Instalacion ins = findById(id).getBody();
        java.sql.Connection conexion=null;
        try{
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, ins.getId());
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
            response.setMessage("error");
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setBody(ins);
            return response;
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                response.setMessage("error");
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setBody(ins);
                return response;
            }
        }
        response.setMessage("OK");
        response.setStatus(HttpStatus.OK);
        response.setBody(ins);
        return response;
    }

    @Override
    public CustomResponseEntity<List<Instalacion>> findAll() {
        CustomResponseEntity<List<Instalacion>> response = new CustomResponseEntity<>();
        List<Instalacion> List = new ArrayList<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_INSTALACION);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Instalacion i = new Instalacion(
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(5),
                        rs.getString(4),
                        rs.getString(6)
                );
                i.setId(rs.getInt(1));
                List.add(i);
            }
            response.setBody(List);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public void UpdateFecha(String instalacionFecha, int IdVenta) {
        String fecha = instalacionFecha + " 00:00:00.000";
        java.sql.Connection conexion=null;
        try{
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE_FECHA);
            ps.setString(1, fecha);
            ps.setString(2, fecha);
            ps.setInt(3, IdVenta);
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
    }
}
