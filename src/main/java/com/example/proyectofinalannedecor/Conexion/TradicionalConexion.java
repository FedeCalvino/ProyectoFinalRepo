package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.*;
import com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Ganchos;
import com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Pinza;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Soporte;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class TradicionalConexion implements IConexion<Tradicional>{
    private static final String SQL_INSERT = "INSERT INTO TRADICIONAL(ID_CORTINA,ID_GANCHO,ID_PINZA,ANCHO_DERECHO,ALTO_DERECHO,CANTIDAD_PANOS,CANTIDAD_ALTOS) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_TRADICIONAL_ARTICULO = "SELECT * FROM TRADICIONAL T JOIN CORTINA C ON C.ID_CORTINA=T.ID_CORTINA WHERE C.ID_ARTICULO=?";
    private static final String SQL_DELETE = "DELETE FROM TRADICIONAL WHERE ID_TRADICIONAL = ?";
    private static final String SQL_BY_ID = "SELECT * FROM TRADICIONAL T JOIN CORTINA C ON C.ID_CORTINA=T.ID_CORTINA WHERE T.ID_TRADICIONAL = ?";

    @Override
    public CustomResponseEntity<Tradicional> save(Tradicional tradicional) {
        CustomResponseEntity<Tradicional> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tradicional.getId());
            ps.setInt(2, tradicional.getGanchos().getIdGanchos());
            ps.setInt(3, tradicional.getPinza().getIdPinza());
            ps.setBigDecimal(4, tradicional.getAnchoDerecho());
            ps.setBigDecimal(5, tradicional.getAltoDerecho());
            ps.setInt(6, tradicional.getCantidadPanos());
            ps.setInt(7, tradicional.getCantidadAltos());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                tradicional.setTradicionalId(generatedId);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar el tradicional");
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.setStatus(HttpStatus.OK);
        response.setBody(tradicional);
        return response;
    }

    @Override
    public CustomResponseEntity<Tradicional> findById(Integer id) {
        CustomResponseEntity<Tradicional> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Tradicional t = new Tradicional (
                        "Tradicional",
                        rs.getFloat("ALTO"),
                        rs.getFloat("ANCHO"),
                        new Ganchos("",rs.getInt("ID_GANCHO")),
                        rs.getInt("ID_TIPO_TELA"),
                        rs.getInt("CANTIDAD_PANOS"),
                        rs.getInt("CANTIDAD_ALTOS"),
                        rs.getString("AMBIENTE"),
                        rs.getString("DETALLES"),
                        0,
                        rs.getBigDecimal("ANCHO_DERECHO"),
                        rs.getBigDecimal("ALTO_DERECHO"),
                        new Pinza("",rs.getInt("ID_PINZA")),
                        ""
                );
                t.setTradicionalId(rs.getInt("ID_TRADICIONAL"));

                response.setBody(t);
                response.setStatus(HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Tradicional> update(Tradicional tradicional) {
        return null;
    }

    @Override
    public CustomResponseEntity<Tradicional> delete(Integer id) {
        CustomResponseEntity<Tradicional> response = new CustomResponseEntity<>();
        java.sql.Connection conexion=null;
        try{
            Tradicional t = this.findById(id).getBody();
            if(t!=null) {
                conexion = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_DELETE);
                ps.setInt(1, id);
                ps.execute();
                response.setBody(t);
                response.setStatus(HttpStatus.OK);
                response.setMessage("tradicional eliminado");
            }else{
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("tradicional no encontrado");
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
    public CustomResponseEntity<List<Tradicional>> findAll() {
        return null;
    }

    public CustomResponseEntity<Tradicional> findTradicionalArticulo(Articulo articulo) {
        CustomResponseEntity<Tradicional> response = new CustomResponseEntity<>();

        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TRADICIONAL_ARTICULO);
            statement.setInt(1,articulo.getIdArticulo());
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Tradicional t = new Tradicional (
                        "Tradicional",
                        rs.getFloat("ALTO"),
                        rs.getFloat("ANCHO"),
                        new Ganchos("",rs.getInt("ID_GANCHO")),
                        rs.getInt("ID_TIPO_TELA"),
                        rs.getInt("CANTIDAD_PANOS"),
                        rs.getInt("CANTIDAD_ALTOS"),
                        rs.getString("AMBIENTE"),
                        rs.getString("DETALLES"),
                        0,
                        rs.getBigDecimal("ANCHO_DERECHO"),
                        rs.getBigDecimal("ALTO_DERECHO"),
                        new Pinza("",rs.getInt("ID_PINZA")),
                        ""
                );
                t.setTradicionalId(rs.getInt("ID_TRADICIONAL"));

                response.setBody(t);
                response.setStatus(HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                return response;
            }
        }
        return response;
    }
}
