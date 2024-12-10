package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Instalacion;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InstalacionConexion implements IConexion<Instalacion>{
    private static final String SQL_DELETE = "DELETE FROM INSTALACION WHERE ID_INSTALACION = ?";
    private static final String SQL_INSERT_INSTALACION = "INSERT INTO INSTALACION(ID_VENTA,COMIENZO,FINAL,ACLARACIONES,TITULO,DIA) VALUES (?,?,?,?,?,?)";
    private static final String SQL_GET_INSTALACION = "select * from INSTALACION";

    @Override
    public CustomResponseEntity<Instalacion> save(Instalacion instalacion) {
        java.sql.Connection conexion=null;
        System.out.println(instalacion.toString());
        CustomResponseEntity<Instalacion> response = new CustomResponseEntity<>();

        try{
        java.sql.Date sqlDate = new java.sql.Date(instalacion.getDia().getTime());

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(sqlDate);
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 1);
        java.sql.Date sqlDateMas1Dia = new java.sql.Date(calendar.getTimeInMillis());

        conexion = (java.sql.Connection) Conexion.GetConexion();
        PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_INSTALACION, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,instalacion.getIdVenta());
        ps.setString(2,instalacion.getStart());
        ps.setString(3,instalacion.getEnd());
        ps.setString(4,instalacion.getAclaraciones());
        ps.setString(5,instalacion.getTitle());
        ps.setDate(6,sqlDateMas1Dia);
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

    @Override
    public CustomResponseEntity<Instalacion> findById(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Instalacion> update(Instalacion instalacion) {
        return null;
    }

    @Override
    public CustomResponseEntity<Instalacion> delete(Integer id) {
        return null;
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
                        rs.getDate(7),
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
}
