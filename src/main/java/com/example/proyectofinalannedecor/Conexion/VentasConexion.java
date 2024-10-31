/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinalannedecor.Conexion;


import com.example.proyectofinalannedecor.Clases.Venta;
import com.sun.jdi.connect.spi.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author calvi
 */
public class VentasConexion implements IConexion<Venta>{
    private static final String SQL_INSERT = "INSERT INTO VENTA(CLIENTE_ID, FECHA, PRECIO, OBRA, FECHA_INSTALACION) VALUES(?, ?, ?, ?, ?)";;

    public static Connection conexion;

    @Override
    public Venta save(Venta v) {
        java.sql.Connection conexion=null;
        try{
            System.out.println(v.getObra());
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, v.getCliente().getId());
            Date fecha = Date.valueOf(LocalDate.now());
            ps.setDate(2, fecha);
            ps.setInt(3, v.getPrecioFinal());
            ps.setString(4, v.getObra());
            ps.setDate(5, v.getFechaInstalacion());
            ps.execute();
            v.setFecha(fecha);

            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                v.setId(rs.getInt(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conexion.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return v;
    }


    @Override
    public Venta findById(Integer id) {
        return null;
    }

    @Override
    public Venta Update(Venta venta) {
        return null;
    }

    @Override
    public Venta delete(Integer id) {
        return null;
    }

    @Override
    public List<Venta> findAll() {
        return null;
    }

/*
    public List<Cortina> SaveCortinasVenta(int idVenta , List<Cortina> cortinas) {
        java.sql.Connection conexion=null;
        try{
            conexion = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement ps = conexion.prepareStatement(SQL_ADD_CORTINA_VENTA);
            Iterator<Cortina> it = cortinas.iterator();
            Venta v = this.findById(idVenta);
            while (it.hasNext()) {
                Cortina c = it.next();
                if (c != null && v!=null){
                    ps.setInt(1, idVenta);
                    ps.setInt(2, c.getId());
                    v.AddCortina(c);
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
    /*


    public List<DtoVentacortinaRoller> findAllRollers(int idVenta) {
        List<DtoVentacortinaRoller> CorinasventasDto = new ArrayList<>();
        java.sql.Connection connection = null;
        System.out.println(idVenta);
        try{
            connection = (java.sql.Connection) Conexion.GetConexion();
            PreparedStatement statement = connection.prepareStatement(SQL_Select_All_VentaDto_ROLLER);
            statement.setInt(1, idVenta);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                DtoVentacortinaRoller dtoC = new DtoVentacortinaRoller();
                dtoC.setAmbiente(rs.getString(1));
                dtoC.setAltoCortina(rs.getString(2),rs.getString(11));
                dtoC.setAnchoCortina(rs.getString(3));
                dtoC.setMotorizada(rs.getByte(4));
                dtoC.setNombreTela(rs.getString(5));
                dtoC.setColorTela(rs.getString(6));
                dtoC.setCano(rs.getString(11));
                dtoC.setIdCortina(rs.getInt(13));
                dtoC.setPosicion(rs.getString(14));
                dtoC.setLadoCadena(rs.getString(15));
                dtoC.setDetalle(rs.getString(16));
                dtoC.setNumeroCortina(rs.getInt(17));
                EstadoCortina ec = new EstadoCortina();
                ec.SetProbadoByte(rs.getByte(9));
                ec.SetArmadoByte(rs.getByte(7));
                ec.SetTelaCByte(rs.getByte(10));
                ec.SetCanoCotadoByte(rs.getByte(8));
                dtoC.setEstado(ec);
                CorinasventasDto.add(dtoC);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                connection.close();
            }catch(Exception e){

            }
        }
        return CorinasventasDto;
    }




    public VentaCortinasDto findAllDtoVC(int idVenta) {
        VentaCortinasDto returnDto = new VentaCortinasDto();
        List<DtoVentacortinaRoller> rollers = findAllRollers(idVenta);
        returnDto.setRollers(rollers);
        List<DtoVentaRiel> rieles = findAllRieles(idVenta);
        returnDto.setRieles(rieles);
        List<DtoVentacortinaTradicional> tradicionales = findAllTradicionalesVenta(idVenta);
        returnDto.setTradicionals(tradicionales);
        return returnDto;
    }

*/
}

