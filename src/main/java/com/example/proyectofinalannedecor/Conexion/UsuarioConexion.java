package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Usuario;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

public class UsuarioConexion {
    private static final String SQL_USUARIO ="select * from [User] u WHERE u.NOMBRE=? and u.PASSWORD=?";

    public CustomResponseEntity<Usuario> Login(Usuario us) {
        System.out.println(us.getNombre());
        System.out.println(us.getPassword());
        CustomResponseEntity<Usuario> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        Usuario u = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_USUARIO);
            statement.setString(1,us.getNombre());
            statement.setString(2,us.getPassword());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                u = new Usuario(rs.getString(2),rs.getString(3));
                u.setId(rs.getInt(1));
            }
            response.setStatus(HttpStatus.OK);
            response.setBody(u);
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            response.setBody(us);
            return response;
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                response.setBody(us);
                return response;
            }
        }
        return response;
    }
}
