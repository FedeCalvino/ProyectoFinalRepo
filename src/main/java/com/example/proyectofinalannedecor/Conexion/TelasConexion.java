package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoTela;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelasConexion implements IConexion<TipoTela>{

    private static final String SQL_SELECT_ALL = "SELECT * FROM TELA";
    private static final String SQL_BY_ID = "SELECT * FROM TELA WHERE ID_TELA = ?";


    @Override
    public CustomResponseEntity<TipoTela> save(TipoTela tipoTela) {
        return null;
    }

    @Override
    public CustomResponseEntity<TipoTela> findById(Integer id) {
        java.sql.Connection connection = null;
        CustomResponseEntity<TipoTela> response = new CustomResponseEntity<>();
        TipoTela tt=null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                tt = new TipoTela (rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
                tt.setId(rs.getInt(1));
            }
            response.setBody(tt);
        }catch(Exception e){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<TipoTela> update(TipoTela tipoTela) {
        return null;
    }

    @Override
    public CustomResponseEntity<TipoTela> delete(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<List<TipoTela>> findAll() {
        CustomResponseEntity<List<TipoTela>> response = new CustomResponseEntity<>();
        List<TipoTela> TiposTela = new ArrayList<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                TipoTela  tt = new TipoTela (rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
                tt.setId(rs.getInt(1));
                TiposTela.add(tt);
            }
            response.setBody(TiposTela);
            response.setStatus(HttpStatus.OK);
        }catch(Exception e){
response.setStatus(HttpStatus.BAD_REQUEST);
response.setMessage(e.getMessage());
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
            }
        }
        return response;
    }
}
