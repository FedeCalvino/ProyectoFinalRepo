package com.example.proyectofinalannedecor.Clases.Orden;

import com.example.proyectofinalannedecor.Clases.Articulo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Orden {

    private int idOrden;
    private Articulo articulo;
    private EstadosPasosOrden estado;
    private java.sql.Date fechaFinalizado;
    private java.sql.Date fechacCreacion;
    private List<PasoOrden> pasos;

    public Orden(int idOrden,List<PasoOrden> pasos,Articulo articulo,EstadosPasosOrden estado){
        this.idOrden=idOrden;
        this.articulo = articulo;
        this.estado = estado;
        this.pasos = pasos;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public Date getFechacCreacion() {
        return fechacCreacion;
    }

    public void setFechacCreacion(Date fechacCreacion) {
        this.fechacCreacion = fechacCreacion;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public EstadosPasosOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadosPasosOrden estado) {
        this.estado = estado;
    }

    public java.sql.Date getFechaFinalizado() {
        return fechaFinalizado;
    }

    public void setFechaFinalizado(java.sql.Date fechaFinalizado) {
        this.fechaFinalizado = fechaFinalizado;
    }

    public List<PasoOrden> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoOrden> pasos) {
        this.pasos = pasos;
    }
}

