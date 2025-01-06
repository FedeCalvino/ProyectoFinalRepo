package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Instalacion;
import com.example.proyectofinalannedecor.Clases.Orden.Lote;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Clases.Orden.PasoOrden;
import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoteConexion implements IConexion<Lote>{

    private static final String SQL_INSERT_LOTE = "INSERT INTO LOTE(NOMBRE,FECHA) VALUES (?,?)";
    private static final String SQL_UPDATE_LOTE = "UPDATE LOTE SET FECHA =? ,NOMBRE = ? WHERE ID_LOTE = ?";
    private static final String SQL_GET_LOTES = "SELECT * FROM LOTE";
    private static final String SQL_GET_LOTES_FECHA = "SELECT * FROM LOTE WHERE FECHA_COMIENZO=?";

    private static final String INSERT_PASO_LOTE = "INSERT INTO LOTE_PASO (ID_LOTE,ID_PASO)";
    @Override
    public CustomResponseEntity<Lote> save(Lote lote) {
        CustomResponseEntity<Lote> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_LOTE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,lote.getNombre());
            ps.setDate(2,lote.getFecha());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int generatedId = rs.getInt(1);
                lote.setIdlote(generatedId);
            }else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo crear el lote");
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
        response.setBody(lote);
        return response;
    }

    public Boolean savePasoLote(PasoOrden pasoorden, Lote lote) {

        java.sql.Connection conexion = null;

        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(INSERT_PASO_LOTE);
            ps.setInt(1, pasoorden.getIdPasoOrden());
            ps.setInt(2, lote.getIdlote());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int generatedId = rs.getInt(1);
                pasoorden.setIdPasoOrden(generatedId);
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public CustomResponseEntity<Lote> findById(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Lote> update(Lote lote) {
        CustomResponseEntity<Lote> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;

        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE_LOTE);

            LocalDate fechaComienzo = lote.getFecha().toLocalDate();
            LocalDate fechaSumada = fechaComienzo.plusDays(1);
            ps.setDate(1, Date.valueOf(fechaSumada));
            ps.setString(2,lote.getNombre());
            ps.setInt(3,lote.getIdlote());

            ps.execute();

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
        response.setBody(lote);
        return response;
    }

    @Override
    public CustomResponseEntity<Lote> delete(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<List<Lote>> findAll() {
        CustomResponseEntity<List<Lote>> response = new CustomResponseEntity<>();
        List<Lote> List = new ArrayList<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_LOTES);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lote l = new Lote(new ArrayList<>(),rs.getString(4),rs.getDate(5));
                l.setIdlote(rs.getInt(1));
                List.add(l);
            }
            response.setBody(List);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public CustomResponseEntity<List<Lote>> findAllFECHA(String fechaComienzo) {
        CustomResponseEntity<List<Lote>> response = new CustomResponseEntity<>();
        List<Lote> List = new ArrayList<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_LOTES_FECHA);
            statement.setString(1,fechaComienzo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lote l = new Lote(new ArrayList<>(),rs.getString(4),rs.getDate(5));
                l.setIdlote(rs.getInt(1));
                List.add(l);
            }
            response.setBody(List);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}
