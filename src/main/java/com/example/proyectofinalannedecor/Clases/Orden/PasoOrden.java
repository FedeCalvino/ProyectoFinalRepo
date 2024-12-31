package com.example.proyectofinalannedecor.Clases.Orden;

import com.example.proyectofinalannedecor.Clases.Empleado;

import java.sql.Date;

public class PasoOrden {
    private int IdPasoOrden;
    private Boolean terminada;
    private Date Diafinalizado;
    private Empleado responsable;
    private PasosArticulo Paso;
    private Date fechaCreacion;

    public PasoOrden(Boolean terminada, Date diafinalizado, PasosArticulo paso) {
        this.terminada = terminada;
        Diafinalizado = diafinalizado;
        Paso = paso;
    }

    public Date getDiafinalizado() {
        return Diafinalizado;
    }

    public void setDiafinalizado(Date diafinalizado) {
        Diafinalizado = diafinalizado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public PasosArticulo getPaso() {
        return Paso;
    }

    public void setPaso(PasosArticulo paso) {
        Paso = paso;
    }

    public int getIdPasoOrden() {
        return IdPasoOrden;
    }

    public void setIdPasoOrden(int idPasoOrden) {
        IdPasoOrden = idPasoOrden;
    }

    // Getters y Setters
    public Boolean getTerminada() {
        return terminada;
    }

    public void setTerminada(Boolean terminada) {
        this.terminada = terminada;
    }

    public Date getDiaFinalizado() {
        return Diafinalizado;
    }

    public void setFinalizado(Date finalizado) {
        this.Diafinalizado = finalizado;
    }

    public Empleado getResponsable() {
        return responsable;
    }

    public void setResponsable(Empleado responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "PasoOrden{" +
                "terminada=" + terminada +
                ", finalizado=" + Diafinalizado +
                ", responsable=" + responsable +
                ", Paso=" + Paso +
                '}';
    }
}

