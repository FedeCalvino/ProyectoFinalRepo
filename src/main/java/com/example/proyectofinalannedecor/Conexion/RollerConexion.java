package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.*;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Soporte;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class RollerConexion implements IConexion<Roller> {
    private static final String SQL_SELECT_ROLLER_ARTICULO = "SELECT * FROM ROLLER R JOIN CORTINA C ON C.ID_CORTINA=R.ID_CORTINA WHERE C.ID_ARTICULO=?";
    private static final String SQL_INSERT = "INSERT INTO ROLLER(ID_CORTINA,ID_CADENA,ID_MOTOR,ID_POSICION,ID_LADO_CADENA,ID_CANO,ID_TIPO_SOPORTE) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE= "UPDATE ROLLER SET ID_CADENA = ? ,ID_MOTOR = ?, ID_POSICION = ?, ID_LADO_CADENA = ? ,ID_CANO = ? ,ID_TIPO_SOPORTE = ? WHERE ID_CORTINA = ?;";
    private static final String SQL_DELETE = "DELETE FROM ROLLER WHERE ID_ROLLER = ?";
    private static final String SQL_BY_ID = "SELECT * FROM ROLLER WHERE ID_ROLLER = ?";

    Byte truebyte=1;
    @Override
    public CustomResponseEntity<Roller> save(Roller R) {
        System.out.println(R.toString());
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, R.getId());
            ps.setInt(2, R.getTipoCadena().getIdTipoCadena());
            ps.setInt(3, R.getMotorRoller().getIdMotor());
            ps.setInt(4, R.getPosicion().getPosicionId());
            ps.setInt(5, R.getLadoCadena().getLadoId());
            ps.setInt(6, R.getCano().getId());
            ps.setInt(7, R.getSoporte().getIdTipo());
            // Ejecutar la inserción
            ps.execute();

            // Obtener el ID generado para el Roller
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                //no lo seteo porque me quedo con el id de la cortina solo chequeo que se cree
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar el roller");
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            // Cerrar la conexión en el bloque finally
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.setStatus(HttpStatus.OK);
        response.setBody(R);
        return response;
    }

    @Override
    public CustomResponseEntity<Roller> findById(Integer id) {
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Roller r = new Roller (
                        "Roller",
                        "",
                        1F,
                        1F,
                        0,
                        new TipoCadena(rs.getInt(3)),
                        new Cano(rs.getInt(7)),
                        new Posicion(rs.getInt(5)),
                        new LadoCadena(rs.getInt(6)),
                        "",
                        1,
                        new Motor(rs.getInt(4)),
                        new Soporte(0,rs.getInt(8),2),""
                );
                r.setIdRoller(rs.getInt(1));
                response.setBody(r);
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
    public CustomResponseEntity<Roller> update(Roller R) {
        return null;
    }

    public CustomResponseEntity<Roller> update(Roller R,int IdCortina) {
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();
        System.out.println(R);
        System.out.println(R.getMotorRoller().getIdMotor());
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setInt(1, R.getTipoCadena().getIdTipoCadena());
            ps.setInt(2, R.getMotorRoller().getIdMotor());
            ps.setInt(3, R.getPosicion().getPosicionId());
            ps.setInt(4, R.getLadoCadena().getLadoId());
            ps.setInt(5, R.getCano().getId());
            ps.setInt(6, R.getSoporte().getIdTipo());
            ps.setInt(7, IdCortina);
            // Ejecutar la inserción
            ps.execute();


        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            // Cerrar la conexión en el bloque finally
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.setStatus(HttpStatus.OK);
        response.setBody(R);
        return response;
    }

    @Override
    public CustomResponseEntity<Roller> delete(Integer id) {
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();
        java.sql.Connection conexion=null;
        try{
            Roller r = this.findById(id).getBody();
            if(r!=null) {
                conexion = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_DELETE);
                ps.setInt(1, id);
                ps.execute();
                response.setBody(r);
                response.setStatus(HttpStatus.OK);
                response.setMessage("rollero eliminado");
            }else{
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("roller no encontrado");
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
    public CustomResponseEntity<List<Roller>> findAll() {
        return null;
    }

    public CustomResponseEntity<Roller> findRollerArticulo(Articulo articulo) {
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();

        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ROLLER_ARTICULO);
            statement.setInt(1,articulo.getIdArticulo());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Roller r = new Roller (
                        articulo.getNombre(),
                        rs.getString(10),
                        rs.getFloat(12),
                        rs.getFloat(11),
                        rs.getInt(13),
                        new TipoCadena(rs.getInt(3)),
                        new Cano(rs.getInt(7)),
                        new Posicion(rs.getInt(5)),
                        new LadoCadena(rs.getInt(6)),
                        rs.getString(14),
                        1,
                        new Motor(rs.getInt(4)),
                        new Soporte(0,rs.getInt(8),2),""
                );
                r.setId(rs.getInt(2));

                r.setIdRoller(rs.getInt(1));
                response.setBody(r);
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



    public boolean deleteFromArticulo(int id) {
        java.sql.Connection conexion=null;
        try{
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                return false;
            }
        }
        return true;
    }

}
