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
    private static final String SQL_DELETE_LOTE = "DELETE LOTE WHERE ID_LOTE = ?";
    private static final String SQL_DELETE_LOTE_PASO = "DELETE LOTE_PASO WHERE ID_LOTE = ?";
    private static final String SQL_DELETE_PASO_LOTE = "DELETE LOTE_PASO WHERE ID_PASO = ?";
    private static final String SQL_GET_LOTES = "SELECT * FROM LOTE";
    private static final String SQL_GET_LOTES_FECHA = "SELECT * FROM LOTE WHERE FECHA=?";

    private static final String INSERT_PASO_LOTE = "INSERT INTO LOTE_PASO (ID_LOTE,ID_PASO) VALUES(?,?)";
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

    public Boolean savePasoLote(int pasoorden, int lote) {
        System.out.println(pasoorden);
        System.out.println(lote);
        java.sql.Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexion = Conexion.GetConexion();
            ps = conexion.prepareStatement(INSERT_PASO_LOTE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lote);
            ps.setInt(2, pasoorden);

            // Execute the insert query
            ps.executeUpdate();

            // Retrieve generated keys (primary key)
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int generatedId = rs.getInt(1);  // This is your generated ID
                return true;  // Successfully inserted
            } else {
                return false;  // No generated key
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log the error
            return false;  // Return false if an error occurs
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (Exception e) {
                e.printStackTrace();  // Log any error during cleanup
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

    public boolean DeleteLotePaaso(Integer id) {
        java.sql.Connection conexion = null;

        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_DELETE_LOTE_PASO);
            ps.setInt(1,id);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
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
        return true;
    }

    public boolean DeletePasoLote(Integer id) {
        java.sql.Connection conexion = null;

        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_DELETE_PASO_LOTE);
            ps.setInt(1,id);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
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
        return true;
    }

    @Override
    public CustomResponseEntity<Lote> delete(Integer id) {
        CustomResponseEntity<Lote> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;

        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_DELETE_LOTE);
            ps.setInt(1,id);

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
        response.setBody(new Lote(new ArrayList<>(),"",Date.valueOf(LocalDate.now())));
        return response;
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
                Lote l = new Lote(new ArrayList<>(),rs.getString(3),rs.getDate(4));
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

    public CustomResponseEntity<List<Lote>> findAllFECHA(String fechaComienzo){
        CustomResponseEntity<List<Lote>> response = new CustomResponseEntity<>();
        List<Lote> List = new ArrayList<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_GET_LOTES_FECHA);
            statement.setString(1,fechaComienzo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lote l = new Lote(new ArrayList<>(),rs.getString(3),rs.getDate(4));
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
