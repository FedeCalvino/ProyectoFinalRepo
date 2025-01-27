package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Bastones;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Soporte;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class RielConexion implements IConexion<Riel>{

    private static final String SQL_INSERT = "INSERT INTO RIEL(AMBIENTE,ID_TIPO_RIEL,ID_ACUMULA,ANCHO,DETALLES,ID_BASTONES,ID_SOPORTE,ID_ARTICULO) VALUES(?,?,?,?,?,?,?,?)";

    private static final String SQL_INSERT_BASTON = "INSERT INTO BASTONES(ID_TIPO_BASTONES,CANTIDAD) VALUES(?,?)";
    private static final String SQL_INSERT_SOPORTE = "INSERT INTO SOPORTE(ID_TIPO_SOPORTE,CANTIDAD) VALUES(?,?)";

    @Override
    public CustomResponseEntity<Riel> save(Riel R) {
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        float ancho = R.getAncho();
        float anchoConTresDecimales = (float) Math.round(ancho * 1000) / 1000;
        try {

            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, R.getAmbiente());
            ps.setInt(2, R.getTipoRiel().getTipoId());
            ps.setInt(3, R.getLadoAcumula().getLadoAcumulaId());
            ps.setFloat(4, anchoConTresDecimales);
            ps.setString(5, R.getDetalle());
            ps.setInt(6, R.getBastones().getIdBatones());
            ps.setInt(7, R.getSoportes().getIdSoporte());
            ps.setInt(8, R.getIdArticulo());

            // Ejecutar la inserción
            ps.execute();

            // Obtener el ID generado para el Roller
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                R.setIdRiel(generatedId);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar el riel");
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
    public CustomResponseEntity<Riel> findById(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> update(Riel riel) {
        return null;
    }

    @Override
    public CustomResponseEntity<Riel> delete(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<List<Riel>> findAll() {
        return null;
    }

    public CustomResponseEntity<Bastones> saveBaston(Bastones baston) {
        CustomResponseEntity<Bastones> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_BASTON, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, baston.getIdtipo());
            ps.setInt(2, baston.getCantidad());
            // Ejecutar la inserción
            ps.execute();

            // Obtener el ID generado para el Roller
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                baston.setIdBatones(generatedId);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar el baston");
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
        response.setBody(baston);
        return response;
    }

    public CustomResponseEntity<Soporte> saveSoportes(Soporte soporte) {
        CustomResponseEntity<Soporte> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_SOPORTE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, soporte.getIdTipo());
            ps.setInt(2, soporte.getCantidad());

            ps.execute();

            // Obtener el ID generado para el Roller
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                soporte.setIdSoporte(generatedId);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar el baston");
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
        response.setBody(soporte);
        return response;
    }
}
