package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Controller.CustomResponseEntity;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteConexion implements IConexion<Cliente>{

    private static final String SQL_INSERT = "INSERT INTO CLIENTES (RUT,NOMBRE,TELEFONO,DIRECCION,TIPO) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM CLIENTES";
    static final String SQL_BY_ID = "SELECT * FROM CLIENTES WHERE ID = ?";


    @Override
    public CustomResponseEntity<Cliente> save(Cliente cliente) {
        CustomResponseEntity<Cliente> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        if(cliente.getNombre() == null){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("El nombre del cliente no puede ser nulo");
        }else {


            try {
                connection = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setBigDecimal(1, cliente.getRut());
                ps.setString(2, cliente.getNombre());
                ps.setBigDecimal(3, cliente.getNumeroTelefono());
                ps.setString(4, cliente.getDireccion());
                ps.setString(5, cliente.getTipo());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {

                }
            }
            response.setStatus(HttpStatus.OK);
            response.setBody(cliente);
        }
        return response;
    }
    @Override
    public CustomResponseEntity<Cliente> findById(Integer id) {
        CustomResponseEntity<Cliente> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        Cliente c=null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                c = new Cliente (rs.getBigDecimal(2),rs.getString(3),rs.getBigDecimal(4),rs.getString(5),rs.getString(7));
                c.setId(rs.getInt(1));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(c!=null){
            response.setStatus(HttpStatus.OK);
            response.setBody(c);
        }else{
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Cliente> update(Cliente cliente) {
        return null;
    }

    @Override
    public CustomResponseEntity<Cliente> delete(Integer id) {
        return null;
    }
    @Override
    public CustomResponseEntity<List<Cliente>> findAll() {
        List<Cliente> Clientes = new ArrayList<Cliente>();
        CustomResponseEntity<List<Cliente>> response = new CustomResponseEntity<>();
        Cliente c =null;
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                //int id, int rut, String nombre, int numeroTelefono, String direccion
                c = new Cliente (rs.getBigDecimal(2),rs.getString(3),rs.getBigDecimal(4),rs.getString(5),rs.getString(7));
                c.setId(rs.getInt(1));
                Clientes.add(c);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(Clientes.isEmpty()) {
            response.setStatus(HttpStatus.NO_CONTENT);
        }else{
            response.setStatus(HttpStatus.OK);
        }
        return response;
    }
}
