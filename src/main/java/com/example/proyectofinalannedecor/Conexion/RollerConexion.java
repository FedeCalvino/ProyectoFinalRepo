package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Clases.Venta;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class RollerConexion implements IConexion<Roller> {
    private static final String SQL_INSERT = "INSERT INTO ROLLER(ID_CORTINA,CADENA_METALICA,CANO,LARGO_CADENA,LADO_CADENA,POSICION) VALUES(?,?,?,?,?,?)";

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
            ps.setNString(4, R.getLargoCadena());
            ps.setString(5, R.getLadoCadena());
            ps.setString(6, R.getPosicion());

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
}
