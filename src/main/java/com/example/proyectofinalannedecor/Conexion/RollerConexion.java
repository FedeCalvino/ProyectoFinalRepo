package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulo;
import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Clases.Venta;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class RollerConexion implements IConexion<Roller> {
    private static final String SQL_SELECT_ROLLER_ARTICULO = "SELECT * FROM ROLLER R JOIN CORTINA C ON R.ID_CORTINA=C.ID_CORTINA WHERE C.ID_ARTICULO=?";
    private static final String SQL_INSERT = "INSERT INTO ROLLER(ID_CORTINA,CADENA_METALICA,CANO,LADO_CADENA,POSICION) VALUES(?,?,?,?,?)";

    @Override
    public CustomResponseEntity<Roller> save(Roller R) {
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, R.getId());
            ps.setByte(2, R.isCadenaMetalicaByte());
            ps.setInt(3, R.getTubo());
            ps.setString(4, R.getLadoCadena());
            ps.setString(5, R.getPosicion());

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
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> update(Roller roller) {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> delete(Integer id) {
        return null;
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
                byte trueBite=1;
                boolean motorizada = rs.getByte(11)==trueBite;
                boolean CadenaMetalica = rs.getByte(3)==trueBite;
                Roller r = new Roller (
                        articulo.getNombre(),
                        rs.getString(8),
                        rs.getFloat(10),
                        rs.getFloat(9),
                        motorizada,
                        rs.getInt(12),
                        CadenaMetalica,
                        rs.getInt(4),
                        rs.getString(6),
                        rs.getString(5),
                        "",
                        0);
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
}
