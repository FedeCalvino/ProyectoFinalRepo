package com.example.proyectofinalannedecor.Clases.Orden;

import com.example.proyectofinalannedecor.Clases.Venta;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Lote{

    private int Idlote;
    private String Estado;
    List<PasoOrden> Pasosordenes = new ArrayList();
    private Date Fecha;
    private String Nombre;
    private List<Venta> Ventas = new ArrayList();

    public Lote(List<PasoOrden> PasosOrdenes,String Nombre, Date fecha){
        this.Fecha=fecha;
        this.Pasosordenes = PasosOrdenes;
        this.Nombre = Nombre;
    }

    public List<Venta> getVentas() {
        return Ventas;
    }

    public void setVentas(List<Venta> ordenes) {
        Ventas = ordenes;
    }

    public int getIdlote() {
        return Idlote;
    }

    public void setIdlote(int idlote) {
        Idlote = idlote;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public List<PasoOrden> getPasosordenes() {
        return Pasosordenes;
    }

    public void setPasosordenes(List<PasoOrden> pasosordenes) {
        this.Pasosordenes = pasosordenes;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Lote{" +
                "Idlote=" + Idlote +
                ", Estado='" + Estado + '\'' +
                ", ordenes=" + Pasosordenes +
                ", Fecha=" + Fecha +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}
