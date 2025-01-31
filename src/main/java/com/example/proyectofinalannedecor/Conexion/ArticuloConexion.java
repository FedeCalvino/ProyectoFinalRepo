package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticuloConexion implements IConexion<Articulo>{
    private static final String SQL_INSERT = "INSERT INTO ARTICULO (NOMBRE,CODIGO,ID_VENTA,NUMERO_ARTICULO,DETALLE_INSTALACION) VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_ARTICULOS_VENTA_ID = "SELECT  * FROM ARTICULO A WHERE A.ID_VENTA=?";
    private static final String SelectArticulosByIdOrden = "SELECT * FROM ARTICULO a JOIN ORDEN o ON o.ID_ARTICULO=a.ID_ARTICULO WHERE ID_ORDEN = ?";
    private static final String SQL_BY_ID = "SELECT * FROM ARTICULO WHERE ID_ARTICULO = ?";
    private static final String SQL_UPDATE = "UPDATE ARTICULO SET DETALLE_INSTALACION = ? WHERE ID_ARTICULO = ?";
    private static final String SQL_DELETE = "DELETE FROM ARTICULO WHERE ID_ARTICULO = ?";
    @Override
    public CustomResponseEntity<Articulo> save(Articulo articulo) {

        CustomResponseEntity<Articulo> response = new CustomResponseEntity<>();

        java.sql.Connection connection = null;

        if(articulo.getNombre() == null){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("El nombre Articulo no tiene nombre");
        }else {
            try {
                connection = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, articulo.getNombre());
                ps.setString(2, articulo.getCodigoBarras());
                ps.setInt(3, articulo.getIdVenta());
                ps.setInt(4, articulo.getNumeroArticulo());
                ps.setString(5, articulo.getDetalleInstalacion());

                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    articulo.setIdArticulo(rs.getInt(1));
                }else{
                    response.setStatus(HttpStatus.BAD_REQUEST);
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
                response.setBody(articulo);
                response.setMessage("error en la conexion");
                return response;
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {

                }
            }
            response.setStatus(HttpStatus.CREATED);
            response.setBody(articulo);
        }
        return response;
    }

    public CustomResponseEntity<List<Articulo>> findArticulosByIdOrden(Integer id){
        CustomResponseEntity<List<Articulo>> response = new CustomResponseEntity<>();
        List<Articulo> articulos =new ArrayList<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SelectArticulosByIdOrden);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Articulo A = new Articulo(rs.getString(2),rs.getInt(5),rs.getString(6));
                A.setIdArticulo(rs.getInt(1));
                A.setCodigoBarras(rs.getString(3));
                articulos.add(A);
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
        if(articulos.isEmpty()){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("no se encontraron articulos");
        }else{
            response.setStatus(HttpStatus.OK);
            response.setBody(articulos);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Articulo> findById(Integer id) {
        CustomResponseEntity<Articulo> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Articulo A = new Articulo(rs.getString(2),rs.getInt(5),rs.getString(6));
                A.setIdArticulo(rs.getInt(1));
                A.setCodigoBarras(rs.getString(3));
                response.setBody(A);
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
    public CustomResponseEntity<Articulo> update(Articulo articulo) {
        System.out.println(articulo.getDetalleInstalacion());
        System.out.println(articulo.getIdArticulo());
        CustomResponseEntity<Articulo> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = (java.sql.Connection) Conexion.GetConexion();

            PreparedStatement ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, articulo.getDetalleInstalacion());
            ps.setFloat(2, articulo.getIdArticulo());

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
        if(articulo!=null){
            response.setBody(articulo);
            response.setStatus(HttpStatus.OK);
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Articulo> delete(Integer id) {
        CustomResponseEntity<Articulo> response = new CustomResponseEntity<>();
        java.sql.Connection conexion=null;
        try{
            Articulo a = this.findById(id).getBody();
            if(a!=null) {
                conexion = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_DELETE);
                ps.setInt(1, id);
                ps.execute();
                response.setBody(a);
                response.setStatus(HttpStatus.OK);
                response.setMessage("Venta eliminada");
            }else{
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("Venta no encontrada");
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
    public CustomResponseEntity<List<Articulo>> findAll() {
        return null;
    }

    public CustomResponseEntity<List<Articulo>> findArticulosVenta(int ventaId) {
        CustomResponseEntity<List<Articulo>> response = new CustomResponseEntity<>();
        List<Articulo> articulos =new ArrayList<>();
        java.sql.Connection connection = null;
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ARTICULOS_VENTA_ID);
            statement.setInt(1, ventaId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Articulo A = new Articulo(rs.getString(2),rs.getInt(5),rs.getString(6));
                A.setIdArticulo(rs.getInt(1));
                A.setCodigoBarras(rs.getString(3));
                articulos.add(A);
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
        if(articulos.isEmpty()){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("no se encontraron ventas");
        }else{
            response.setStatus(HttpStatus.OK);
            response.setBody(articulos);
        }
        return response;
    }


}
