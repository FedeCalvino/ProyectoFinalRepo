package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Cliente;
import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Venta;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class CortinaConexion implements IConexion<Cortina>{
    private static final String SQL_INSERT_CORTINA = "INSERT INTO CORTINA(ALTO,ANCHO,ID_TIPO_TELA,AMBIENTE,DETALLES,ID_ARTICULO) VALUES(?,?,?,?,?,?)";

    private static final String SQL_DELETE = "DELETE FROM CORTINA WHERE ID_ARTICULO = ?";
    private static final String SQL_UPDATE_CORTINA = "UPDATE CORTINA SET ALTO = ?, ANCHO = ?, ID_TIPO_TELA = ?, AMBIENTE = ?, DETALLES = ? WHERE ID_CORTINA = ?;";
    private static final String SQL_UPDATE_ROLLER = "UPDATE ROLLER SET POSICION =? ,LADO_CADENA = ?, CANO = ? WHERE ID_CORTINA = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM CORTINA";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM CORTINAS WHERE ID_CORTINA = ?";
    private static final String SQL_UPDATE_CORTADA="update Estado_cortina set Estado_cortina.TELA_CORTADA =1 From Estado_cortina e join CORTINAS c on c.ESTADO_CORTINA_ID=e.ID where c.ID_CORTINA=?";
    private byte trueBite = 1;
    private byte falseBite = 0;

    @Override
    public CustomResponseEntity<Cortina> save(Cortina C) {
        CustomResponseEntity<Cortina> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            // Redondear valores a tres decimales antes de asignarlos
            BigDecimal alto = new BigDecimal(C.getAlto()).setScale(3, RoundingMode.HALF_UP);
            BigDecimal ancho = new BigDecimal(C.getAncho()).setScale(3, RoundingMode.HALF_UP);

            // Actualizar los valores en el objeto Cortina si es necesario
            C.setAlto(alto.floatValue());
            C.setAncho(ancho.floatValue());

            // Obtener la conexión
            conexion = (java.sql.Connection) Conexion.GetConexion();

            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_CORTINA, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, C.getAlto());
            ps.setFloat(2, C.getAncho());
            ps.setInt(3, C.GetTipoTelaId());
            ps.setString(4, C.getAmbiente());
            ps.setString(5, C.getDetalle());
            ps.setInt(6, C.getIdArticulo());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                C.setId(generatedId);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar el id de la cortina");
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
        if(C!=null){
            response.setBody(C);
            response.setStatus(HttpStatus.OK);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Cortina> findById(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Cortina> update(Cortina C) {
        return null;
    }

    public Boolean deleteFromArticulo(int id) {
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

    public CustomResponseEntity<Cortina> update(Cortina C,int idCortina) {
        CustomResponseEntity<Cortina> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = (java.sql.Connection) Conexion.GetConexion();

            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE_CORTINA);
            ps.setFloat(1, C.getAlto());
            ps.setFloat(2, C.getAncho());
            ps.setInt(3, C.GetTipoTelaId());
            ps.setString(4, C.getAmbiente());
            ps.setString(5, C.getDetalle());
            ps.setInt(6, idCortina);

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
        if(C!=null){
            response.setBody(C);
            response.setStatus(HttpStatus.OK);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Cortina> delete(Integer id) {
        return null;
    }

    @Override
    public CustomResponseEntity<List<Cortina>> findAll() {
        List<Cortina> cortinas = new ArrayList<Cortina>();
        CustomResponseEntity<List<Cortina>> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                    boolean motorizada = rs.getByte(5)==trueBite;
                    Cortina c = new Cortina ("Cortina",rs.getFloat(4),rs.getFloat(3),0,rs.getString(2),rs.getString(7),0,"");
                    c.setId(rs.getInt(1));
                    cortinas.add(c);
            }
            if(cortinas.isEmpty()){

            }else{
                response.setBody(cortinas);
                response.setStatus(HttpStatus.OK);
            }

        }catch(Exception e){

        }finally{
            try{
                connection.close();
            }catch(Exception e){

            }
        }
        return response;
    }
}
