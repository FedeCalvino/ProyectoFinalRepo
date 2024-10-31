package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Conexion.ClienteConexion;
import com.example.proyectofinalannedecor.Conexion.VentasConexion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VentaService{

    private static final VentasConexion VentasConexion= new VentasConexion();
    private static final ClienteConexion ClienteConexion= new ClienteConexion();

    public VentaService() {

    }

    public Venta save(Venta venta) {
        System.out.println(venta);
      /*  Cliente c = ClienteConexion.findById(IdCli);
        venta.setCliente(c);
        return VentasConexion.save(venta);*/
        return null;
    }
    public List<Venta> findAll(){
        List<Venta> ventas = new ArrayList<>();
        return ventas;
    }

/*
    public Venta findById(Integer i){
        return VentasConexion.findById(i);
    }

    public ResponseEntity<Venta> Update(Venta venta) {
        return null;
    }

    public void update(Venta v){
        VentasConexion.Update(v);
    }
    public void delete(Integer id){
        VentasConexion.delete(id);
    }
    public List<Venta> findAll(){
        return VentasConexion.findAll();
    }

    public Cortina SaveCortinasVenta(int IdCortina, int VentaId) {
        return VentasConexion.SaveCortinaVenta(IdCortina,VentaId);
    }

    public List<DtoVenta> findAllDto() {
        return VentasConexion.findAllDto();
    }

    public VentaCortinasDto findAllDtoVC(int idVenta) {
        return VentasConexion.findAllDtoVC(idVenta);
    }

    public List<DtoVenta> findAllDtoLike(String nombreCli) {
        return VentasConexion.findAllDtoLike(nombreCli);
    }

    public List<DtoVenta> findNextDto() {
        return VentasConexion.findNextDto();
    }

    public List<DtoVenta> findSinCorVentaDto() {
        return VentasConexion.findSinCorVentaDto();
    }

    public List<DtoVenta> findSinInsVentaDto() {
        return VentasConexion.findSinInsrVentaDto();
    }

    public void UpdateInstVenta(int idVenta) {
        VentasConexion.UpdateInstVenta(idVenta);
    }

    public List<DtoDatosTela> DatosTela() {
        return VentasConexion.DatosTela();
    }

    public List<DtoDatosTelaMetros> DatosTelaMetros() {
        return VentasConexion.DatosTelaMetros();
    }
    public List<DtoDatosTelaMetrosClientes> DatosTelaMetrosClientes() {
        return VentasConexion.DatosTelaMetrosCliente();
    }

 */
}

