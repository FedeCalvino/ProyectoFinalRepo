package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.*;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdenConexion implements IConexion<Orden>{

    private static final String InsertOrder = "INSERT INTO ORDEN(ID_ARTICULO,ESTADO) VALUES (?,?)";
    private static final String InsertPasoOrder = "INSERT INTO PASO_ORDEN(ID_ORDEN,NOMBRE_PASO,ESTADO_PASO,TERMINADA) VALUES (?,?,?,?)";
    private static final String SelectPasosOrden = "SELECT * FROM PASO_ORDEN WHERE ID_ORDEN = ?";
    private static final String SelectPasosOrdenSinT = "SELECT * FROM PASO_ORDEN WHERE TERMINADA=0 and ID_ORDEN = ?";
    private static final String SelectOrdenes = "SELECT o.FECHA_CREACION,o.ID_ORDEN,o.ID_ARTICULO,o.ESTADO,a.ID_VENTA FROM ORDEN o join articulo a on o.ID_ARTICULO=a.ID_ARTICULO";
    private static final String SQL_SELECT_BY_ID = "SELECT o.FECHA_CREACION,o.ID_ORDEN,o.ID_ARTICULO,o.ESTADO,a.ID_VENTA FROM ORDEN o join articulo a on o.ID_ARTICULO=a.ID_ARTICULO WHERE o.ID_ORDEN = ?";
    private static final String DeleteOrder = "DELETE FROM ORDEN WHERE ORDER_ID = ?";
    private static final String UpdatePasoOrden = "UPDATE PASO_ORDEN SET TERMINADA = ?, FECHA_FINALIZACION = ? WHERE ID_PASO_ORDEN = ?";
    private static final String UpdateOrden = "UPDATE ORDER SET FECHA_FINALIZADO = ?, SET ESTADO = ? WHERE ID_ORDEN = ?";
    private static final String setOrdenTerminada = "UPDATE PASO_ORDEN SET TERMINADA = 1 WHERE ID_PASO_ORDEN = ?";
    private static final String CheckForUpdateRollerTela_Corte = "SELECT po.TERMINADA FROM PASO_ORDEN po JOIN ORDEN o ON o.ID_ORDEN = po.ID_ORDEN WHERE o.ID_ORDEN = ? AND (po.NOMBRE_PASO = 'CORTE_TELA' OR po.NOMBRE_PASO = 'CORTE_CANO');";
    private static final String SELECT_PASO_ORDEN_LOTE = "SELECT * FROM PASO_ORDEN po join LOTE_PASO lp on lp.ID_PASO=po.ID_PASO_ORDEN where lp.ID_LOTE =  ?";
    private static final String SELECT_ORDEN_ARTICULO = "SELECT * FROM ORDEN WHERE ID_ARTICULO=?";
    private static final String SQL_DELETE_BY_ARTICULOID = "DELETE FROM ARTICULO WHERE ID_ARTICULO = ?";
    private static final String SQL_DELETE_PASO_ORDEN = "DELETE FROM PASO_ORDEN WHERE ID_ORDEN = ?";
    private static final String SQL_DELETE_ORDEN = "DELETE FROM ORDEN WHERE ID_ORDEN = ?";
    private Byte trueByte =1;
    private Byte falseByte =0;

    @Override
    public CustomResponseEntity<Orden> save(Orden orden) {
        CustomResponseEntity<Orden> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;
        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(InsertOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orden.getArticulo().getIdArticulo());
            ps.setString(2, orden.getEstado().name());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int generatedId = rs.getInt(1);
                orden.setIdOrden(generatedId);
            }else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar la orden");
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
        response.setBody(orden);
        return response;
    }
    public boolean CheckPassCorteTela(int ordenId){
        return false;
    }

    public CustomResponseEntity<PasoOrden> savePasoOrden(PasoOrden pasoorden,Orden orden) {
        CustomResponseEntity<PasoOrden> response = new CustomResponseEntity<>();
        java.sql.Connection conexion = null;

        try {
            conexion = Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(InsertPasoOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orden.getIdOrden());
            ps.setString(2, pasoorden.getPaso().name());
            ps.setString(3, "Pendiente");
            ps.setByte(4, falseByte);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                int generatedId = rs.getInt(1);
                pasoorden.setIdPasoOrden(generatedId);
            }else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo agregar el paso");
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
        response.setBody(pasoorden);
        return response;
    }



    @Override
    public CustomResponseEntity<Orden> findById(Integer id) {
        CustomResponseEntity<Orden> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;

        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Orden orden = new Orden(rs.getInt(2),new ArrayList<>(),null,rs.getInt(3));
                orden.setFechacCreacion(rs.getDate(1));
                orden.setEstado(EstadosPasosOrden.valueOf(rs.getString(4)));
                orden.setIdVenta(rs.getInt(5));
                response.setBody(orden);
                response.setStatus(HttpStatus.OK);
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                return response;
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<Orden> update(Orden orden) {
        CustomResponseEntity<Orden> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = connection.prepareStatement(UpdateOrden);

            ps.setDate( 1, orden.getFechaFinalizado());

            ps.setString(2, orden.getEstado().name());
            ps.execute();
            response.setStatus(HttpStatus.OK);
            response.setBody(orden);

        } catch (Exception e) {

            e.printStackTrace();
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
            response.setBody(null);
            response.setMessage("error en la conexion");
            return response;

        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setBody(orden);
            }

        }
        return response;
    }

        public CustomResponseEntity<PasoOrden> updatePasoOrden(PasoOrden pasoOrden){
            CustomResponseEntity<PasoOrden> response = new CustomResponseEntity<>();
            java.sql.Connection connection = null;
            try {
                connection = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = connection.prepareStatement(UpdatePasoOrden);
                ps.setDate(2, pasoOrden.getDiaFinalizado());

                if(pasoOrden.getTerminada())
                    ps.setByte(1, trueByte);
                else
                    ps.setByte(1, falseByte);

                ps.setInt(3,pasoOrden.getIdPasoOrden());
                ps.execute();
                response.setStatus(HttpStatus.OK);
                response.setBody(pasoOrden);

            } catch (Exception e) {

                e.printStackTrace();
                response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
                response.setBody(null);
                response.setMessage("error en la conexion");
                return response;

            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    response.setStatus(HttpStatus.BAD_REQUEST);
                    response.setBody(pasoOrden);
                }

            }
            return response;
        }


    @Override
    public CustomResponseEntity<Orden> delete(Integer id) {
        return null;
    }

    public CustomResponseEntity<List<PasoOrden>> selectPasosOrden(Integer id) {

        CustomResponseEntity<List<PasoOrden>> response = new CustomResponseEntity<>();
        List<PasoOrden> listaPasoOrden = new ArrayList<>();
        java.sql.Connection connection = null;

        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SelectPasosOrden);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PasoOrden po = new PasoOrden(rs.getInt(1),rs.getByte(5) == trueByte, rs.getDate(6), PasosArticulo.valueOf(rs.getString(2).toUpperCase()));
                listaPasoOrden.add(po);
            }
            response.setBody(listaPasoOrden);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                return response;
            }
        }
        return response;
    }

    @Override
    public CustomResponseEntity<List<Orden>> findAll() {

        CustomResponseEntity<List<Orden>> response = new CustomResponseEntity<>();
        List<Orden> ListOrdenes = new ArrayList<>();
        java.sql.Connection connection = null;

        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SelectOrdenes);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Orden orden = new Orden(0,new ArrayList<>(),null,rs.getInt(3));
                orden.setFechacCreacion(rs.getDate(1));
                orden.setIdOrden(rs.getInt(2));
                orden.setEstado(EstadosPasosOrden.valueOf(rs.getString(4)));
                orden.setIdVenta(rs.getInt(5));
                ListOrdenes.add(orden);
            }

            response.setBody(ListOrdenes);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                return response;
            }
        }
        return response;
    }
    public int FindCantidadTelaCorteSinFinalizarRoller() {
        /*CustomResponseEntity<List<Orden>> response = new CustomResponseEntity<>();
        List<Orden> ListOrdenes = new ArrayList<>();
        java.sql.Connection connection = null;

        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(CheckForUpdateRollerTela_Corte);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Orden orden = new Orden(0,new ArrayList<>(),new Articulo(""),null);
                orden.setFechacCreacion(rs.getDate(4));
                orden.setIdOrden(rs.getInt(1));
                Articulo artic = new Articulo("");
                artic.setIdArticulo(rs.getInt(2));
                orden.setArticulo(artic);
                orden.setEstado(EstadosPasosOrden.valueOf(rs.getString(3)));
                ListOrdenes.add(orden);
            }

            response.setBody(ListOrdenes);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                return response;
            }
        }
        return response;*/

         return 1;
    }

    public CustomResponseEntity<List<PasoOrden>> GetOrdenesLote(Lote l) {

        CustomResponseEntity<List<PasoOrden>> response = new CustomResponseEntity<>();

        List<PasoOrden> listaPasoOrden = new ArrayList<>();
        java.sql.Connection connection = null;

        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SELECT_PASO_ORDEN_LOTE);
            statement.setInt(1, l.getIdlote());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PasoOrden po = new PasoOrden(rs.getInt(1),rs.getByte(5) == trueByte, rs.getDate(6), PasosArticulo.valueOf(rs.getString(2).toUpperCase()));
                po.setIdOrden(rs.getInt(3));
                listaPasoOrden.add(po);
            }
            response.setBody(listaPasoOrden);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                return response;
            }
        }
        return response;
    }

    public CustomResponseEntity<List<PasoOrden>> selectPasosOrdenSinTerminar(int idOrden) {

        CustomResponseEntity<List<PasoOrden>> response = new CustomResponseEntity<>();
        List<PasoOrden> listaPasoOrden = new ArrayList<>();
        java.sql.Connection connection = null;

        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SelectPasosOrdenSinT);
            statement.setInt(1, idOrden);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PasoOrden po = new PasoOrden(rs.getInt(1),rs.getByte(5) == trueByte, rs.getDate(6), PasosArticulo.valueOf(rs.getString(2).toUpperCase()));
                listaPasoOrden.add(po);
            }
            response.setBody(listaPasoOrden);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage(e.getMessage());
                return response;
            }
        }
        return response;

    }

    public boolean UpdatePaso(int id) {
        CustomResponseEntity<Orden> response = new CustomResponseEntity<>();
        java.sql.Connection connection = null;
        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = connection.prepareStatement(setOrdenTerminada);

            ps.setInt( 1, id);

            ps.execute();
            response.setStatus(HttpStatus.OK);
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
            response.setBody(null);
            response.setMessage("error en la conexion");
            return false;

        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                response.setStatus(HttpStatus.BAD_REQUEST);
                return false;
            }

        }
    }

    public void deleteOrdenArticuloId(int idArticulo) {
        java.sql.Connection conexion=null;
        try{
                conexion = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_DELETE_ORDEN);
                ps.setInt(1, idArticulo);
                ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
            }
        }
    }

    public Orden finOrdenByArticulo(int idArticulo) {
        java.sql.Connection connection = null;

        try {
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SELECT_ORDEN_ARTICULO);
            statement.setInt(1, idArticulo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Orden o = new Orden(rs.getInt(1),null, EstadosPasosOrden.valueOf(rs.getString(3)), rs.getInt(2));
                return o;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void deletePasosOrden(int idOrden) {
        java.sql.Connection conexion=null;
        try{
                conexion = (java.sql.Connection) Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_DELETE_PASO_ORDEN);
                ps.setInt(1, idOrden);
                ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
