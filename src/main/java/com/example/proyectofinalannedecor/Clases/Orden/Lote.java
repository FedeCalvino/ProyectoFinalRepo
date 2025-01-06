package com.example.proyectofinalannedecor.Clases.Orden;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Lote{

    private int Idlote;
    private String Estado;
    List<PasoOrden> ordenes = new ArrayList();
    private Date Fecha;
    private String Nombre;

    public Lote(List<PasoOrden> PasosOrdenes,String Nombre, Date fecha){
        this.Fecha=fecha;
        this.ordenes = PasosOrdenes;
        this.Nombre = Nombre;
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

    public List<PasoOrden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<PasoOrden> ordenes) {
        this.ordenes = ordenes;
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
}
