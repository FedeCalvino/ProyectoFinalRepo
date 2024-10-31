package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Cliente;

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
    public Cliente save(Cliente cliente) {
        System.out.println(cliente.getRut());
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, cliente.getRut());
            ps.setString(2,cliente.getNombre());
            ps.setBigDecimal(3, cliente.getNumeroTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTipo());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                cliente.setId(rs.getInt(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(Exception e){

            }
        }
        System.out.println(cliente);
        return cliente;
    }
    @Override
    public Cliente findById(Integer id) {
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
        return c;
    }

    @Override
    public Cliente Update(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente delete(Integer id) {
        return null;
    }
    @Override
    public List<Cliente> findAll() {
        List<Cliente> Clientes = new ArrayList<Cliente>();
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
        return Clientes;
    }
}
