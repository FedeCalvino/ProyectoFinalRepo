package com.example.proyectofinalannedecor.Clases;

import java.sql.Date;
import java.util.ArrayList;

public class Venta {
    private int id;
    private Cliente cliente;
    private ArrayList<Cortina> cortinas = new ArrayList<>();
    public java.sql.Date fecha;
    public Integer precioFinal;
    public Date fechaInstalacion;
    public String obra;

    public Venta(Cliente cliente, ArrayList<Cortina> cortinas, Date fecha, Date fechaInstalacion, Integer precioFinal, String obra) {
        this.cliente = cliente;
        this.cortinas = cortinas;
        this.fecha = fecha;
        this.fechaInstalacion = fechaInstalacion;
        this.obra = obra;
        this.precioFinal = precioFinal;
    }

    public String getObra() {
        return obra;
    }

    public void setObra(String obra) {
        this.obra = obra;
    }

    public Date getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(Date fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPrecioFinal() {
        return precioFinal;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public int compareTo(Venta r) {
        if (r.precioFinal > this.precioFinal) {
            return 1;
        } else if (r.precioFinal.equals(this.precioFinal)) {
            return 0;
        }
        return -1;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCortinas(ArrayList<Cortina> cortinas) {
        this.cortinas = cortinas;
    }


    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public void setPrecioFinal(Integer precio) {
        this.precioFinal = precio;
    }

    public ArrayList<Cortina> getListaCortinas() {
        return this.cortinas;
    }

    public void addCortina(Cortina c) {
        this.cortinas.add(c);
    }

    public void setListaCortinas(ArrayList<Cortina> cortinas) {
        this.cortinas = cortinas;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", cortinas=" + cortinas +
                ", fecha=" + fecha +
                ", precioFinal=" + precioFinal +
                ", fechaInstalacion=" + fechaInstalacion +
                ", obra='" + obra + '\'' +
                '}';
    }
}
