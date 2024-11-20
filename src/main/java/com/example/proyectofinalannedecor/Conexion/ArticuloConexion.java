package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulo;
import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ArticuloConexion implements IConexion<Articulo>{
    private static final String SQL_INSERT = "INSERT INTO ARTICULO (NOMBRE,CODIGO) VALUES (?,?)";

    @Override
    public CustomResponseEntity<Articulo> save(Articulo articulo) {
        CustomResponseEntity<Articulo> response = new CustomResponseEntity<>();

        java.sql.Connection connection = null;

        if(articulo.getNombre() == null){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("El nombre Articulo no tiene nombre");
        }else {
            try {
                connection = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, articulo.getNombre());
                ps.setString(2, articulo.getCodigoBarras());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    articulo.setIdArticulo(rs.getInt(1));
                }else{
                    response.setStatus(HttpStatus.BAD_REQUEST);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
                response.setBody(articulo);
                response.setMessage("error en la conexion");
                return response;
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {

                }
            }
            response.setStatus(HttpStatus.CREATED);
            response.setBody(articulo);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Articulo> findById(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Articulo> update(Articulo articulo) {
        return null;
    }

    @Override
    public CustomResponseEntity<Articulo> delete(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<List<Articulo>> findAll() {
        return null;
    }
}
