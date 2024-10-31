package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Clases.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class CortinasConexion {
    byte trueBite=1;
    byte falseBite=0;

    private static final String SQL_INSERT_CORTINA = "INSERT INTO CORTINAS(ALTO,ANCHO,TIPO_TELA_ID,ID_ORDEN,MOTORIZADA,AMBIENTE,DETALLES,NUMERO_CORTINA) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_ROLLER = "INSERT INTO ROLLER(ID_CORTINA,CADENA_METALICA,CANO,LARGO_CADENA,LADO_CADENA,POSICION) VALUES(?,?,?,?,?,?)";
    private static final String SQL_ADD_CORTINA_VENTA = "INSERT INTO VENTA_CORTINA(VENTA_ID,CORTINA_ID) VALUES(?,?)";

    public Cortina saveCortina(Cortina C) {
    java.sql.Connection conexion = null;
    try {
        // Obtener la conexión
        conexion = (java.sql.Connection) Conexion.GetConexion();

        // Preparar la sentencia SQL con RETURN_GENERATED_KEYS
        PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_CORTINA, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, C.getAlto());
        ps.setNString(2, C.getAncho());
        ps.setInt(3, C.GetTipoTelaId());
        ps.setString(6, C.getAmbiente());
        ps.setString(7, C.getDetalle());
        ps.setInt(7, C.getNumeroCortina());

        // Establecer si es motorizada o no
        if (C.getMotorizada()) {

            ps.setByte(5, trueBite);
        } else {
            ps.setByte(5, falseBite);
        }

        // Ejecutar la inserción
        ps.execute();

        // Obtener el ID generado y establecerlo en el objeto Cortina
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int generatedId = rs.getInt(1);
            C.setId(generatedId);
            System.out.println("Generated ID for Cortina: " + generatedId);

        } else {
            System.out.println("No generated keys available.");
        }

    } catch (Exception e) {
        e.printStackTrace();
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
    return C;
}

    public List<Cortina> SaveCortinasVenta(Venta v , List<Cortina> cortinas) {
        java.sql.Connection conexion=null;
        try{
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_ADD_CORTINA_VENTA);
            Iterator<Cortina> it = cortinas.iterator();
            while (it.hasNext()) {
                Cortina c = it.next();
                if (c != null && v!=null){
                    ps.setInt(1, v.getId());
                    ps.setInt(2, c.getId());
                    v.addCortina(c);
                }
            }
        }catch(Exception e){

        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return cortinas;
    }


    public List<Roller> saverRollers(List<Roller> rolers) {
        Cortina C = null;
        java.sql.Connection conexion = null;
        VentasConexion con = new VentasConexion();
        for(Roller R :rolers){
            try {
                // Crear la cortina inicialmente
                C = new Cortina(R.getAlto(), R.getAncho(), R.getMotorizada(), R.GetTipoTelaId(), R.getAmbiente(),R.getDetalle(),R.getNumeroCortina());
                int idC = this.saveCortina(C).getId();

                System.out.println("IdCort after save: " + idC);

                // Obtener la conexión y preparar la consulta para insertar en la tabla Roller
                conexion = Conexion.GetConexion();
                PreparedStatement ps = conexion.prepareStatement(SQL_INSERT_ROLLER, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, idC); // ID de la cortina
                ps.setByte(2, R.isCadenaMetalicaByte()); // Cadena metálica
                ps.setInt(3, R.getTubo()); // Tubo
                ps.setNString(4, R.getLargoCadena()); // Largo de la cadena
                ps.setString(5, R.getLadoCadena()); // Lado de la cadena
                ps.setString(6, R.getPosicion()); // Posición

                // Ejecutar la inserción
                ps.execute();

                // Obtener el ID generado para el Roller
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    R.setId(generatedId); // Establecer el ID generado en el objeto Roller
                    System.out.println("Generated ID for Roller: " + generatedId);
                } else {
                    System.out.println("No generated keys available for Roller.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rolers;
    }

}

