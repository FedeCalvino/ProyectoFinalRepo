package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.Bastones;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.LadoAcumula;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.TipoRiel;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Soporte;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class RielConexion implements IConexion<Riel>{
    private static final String SQL_BY_ID = "SELECT * FROM RIEL WHERE ID_RIEL = ?";
    private static final String SQL_INSERT = "INSERT INTO RIEL(AMBIENTE,ID_TIPO_RIEL,ID_ACUMULA,ANCHO,DETALLES,ID_BASTONES,ID_SOPORTE,ID_ARTICULO) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM RIEL WHERE ID_RIEL = ?";
    private static final String SQL_INSERT_BASTON = "INSERT INTO BASTONES(ID_TIPO_BASTONES,CANTIDAD) VALUES(?,?)";
    private static final String SQL_INSERT_SOPORTE = "INSERT INTO SOPORTE(ID_TIPO_SOPORTE,CANTIDAD) VALUES(?,?)";
    private static final String SQL_SELECT_RIEL_ARTICULO = "SELECT * FROM RIEL R WHERE R.ID_ARTICULO=?";
    private static final String SQL_SELECT_SOPORTE_RIEL = "SELECT * FROM SOPORTE S JOIN TIPO_SOPORTE TS ON TS.ID_TIPO_SOPORTE=S.ID_TIPO_SOPORTE WHERE S.ID_SOPORTE=?";
    private static final String SQL_SELECT_BASTONES_RIEL = "SELECT * FROM BASTONES B JOIN TIPO_BASTON TB ON TB.ID_TIPO_BASTON=B.ID_TIPO_BASTONES WHERE B.ID_BASTONES=?";
    private static final String SQL_UPDATE = "UPDATE RIEL SET AMBIENTE = ?, DETALLES = ?, ANCHO = ?, ID_ACUMULA=?, ID_TIPO_RIEL=? WHERE ID_ARTICULO = ?";
    private static final String SQL_UPDATE_SOPORTE = "UPDATE SOPORTE SET ID_TIPO_SOPORTE = ?, CANTIDAD = ? WHERE ID_SOPORTE = ?";
    private static final String SQL_UPDATE_BASTONES = "UPDATE BASTONES SET ID_TIPO_BASTONES = ?, CANTIDAD = ? WHERE ID_BASTONES = ?";

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
            ps.setInt(6, R.getBastones().getIdBastones());
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
    public Bastones findBastonesRiel(int idBaston) {

        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BASTONES_RIEL);

            statement.setInt(1,idBaston);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                Bastones baston = new Bastones(rs.getInt(1),rs.getInt(2),rs.getInt(3));
                baston.setNombre(rs.getString(5));

                return baston;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    public Soporte findSoportesId(int idSoportes) {

        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_SOPORTE_RIEL);

            statement.setInt(1,idSoportes);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                Soporte soporte = new Soporte(rs.getInt(1),rs.getInt(2),rs.getInt(3));

                soporte.setNombre(rs.getString(6));

                return soporte;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            try{
                connection.close();
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    public CustomResponseEntity<Riel> findRielArticulo(Articulo riel) {
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();

        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_RIEL_ARTICULO);
            statement.setInt(1,riel.getIdArticulo());
            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                Riel r = new Riel (
                        riel.getNombre(),
                        rs.getString(2),
                        rs.getFloat(8),
                        new TipoRiel(rs.getInt(3)),
                        new LadoAcumula(rs.getInt(4)),
                        rs.getString(7),
                        this.findBastonesRiel(rs.getInt(5)),
                        this.findSoportesId(rs.getInt(6)),0,""
                );
                r.setIdRiel(rs.getInt(1));
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

    @Override
    public CustomResponseEntity<Riel> findById(Integer id) {
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Riel r = new Riel (
                        "Riel",
                        rs.getString(2),
                        rs.getFloat(8),
                        new TipoRiel(rs.getInt(3)),
                        new LadoAcumula(rs.getInt(4)),
                        rs.getString(7),
                        this.findBastonesRiel(rs.getInt(5)),
                        this.findSoportesId(rs.getInt(6)),0,""
                );
                r.setIdRiel(rs.getInt(1));
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
    public CustomResponseEntity<Riel> updateSoportes(Riel riel) {
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        System.out.println(riel.getSoportes());
        try {
            conexion = (java.sql.Connection) Conexion.GetConexion();

            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE_SOPORTE);
            ps.setInt(1, riel.getSoportes().getIdTipo());
            ps.setInt(2, riel.getSoportes().getCantidad());
            ps.setInt(3, riel.getSoportes().getIdSoporte());
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
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public CustomResponseEntity<Riel> updateBastones(Riel riel) {
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = (java.sql.Connection) Conexion.GetConexion();

            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE_BASTONES);
            ps.setInt(1, riel.getBastones().getIdtipo());
            ps.setInt(2, riel.getBastones().getCantidad());
            ps.setInt(3, riel.getBastones().getIdBastones());
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
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Riel> update(Riel riel) {
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        System.out.println(riel.getLadoAcumula().getLadoAcumulaId());
        try {
            conexion = (java.sql.Connection) Conexion.GetConexion();

            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, riel.getAmbiente());
            ps.setString(2, riel.getDetalle());
            ps.setFloat(3, riel.getAncho());
            ps.setInt(4, riel.getLadoAcumula().getLadoAcumulaId());
            ps.setInt(5, riel.getTipoRiel().getTipoId());
            ps.setInt(6, riel.getIdArticulo());

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
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(riel!=null){
            response.setBody(riel);
            response.setStatus(HttpStatus.OK);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Riel> delete(Integer id) {
        CustomResponseEntity<Riel> response = new CustomResponseEntity<>();
        java.sql.Connection conexion=null;
        try{
            Riel r = this.findById(id).getBody();
            if(r!=null) {
                conexion = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_DELETE);
                ps.setInt(1, id);
                ps.execute();
                response.setBody(r);
                response.setStatus(HttpStatus.OK);
                response.setMessage("riel eliminado");
            }else{
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("riel no encontrado");
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
                baston.setIdBastones(generatedId);
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
