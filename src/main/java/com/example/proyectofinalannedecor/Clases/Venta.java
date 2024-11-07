package com.example.proyectofinalannedecor.Clases;

import java.sql.Date;
import java.util.ArrayList;

public class Venta {
    private int id;
    private Cliente cliente;
    private ArrayList<Cortina> cortinas = new ArrayList<>();
    public java.sql.Date fecha;
    public Integer precio;
    public Date fechaInstalacion;
    public String obra;

    public Venta(Integer Id,Cliente cliente, ArrayList<Cortina> cortinas, Date fecha, Date fechaInstalacion, Integer precioFinal, String obra) {
        this.id = id;
        this.cliente = cliente;
        this.cortinas = cortinas;
        this.fecha = fecha;
        this.fechaInstalacion = fechaInstalacion;
        this.obra = obra;
        this.precio = precioFinal;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrecio() {
        return precio;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public int compareTo(Venta r) {
        if (r.precio > this.precio) {
            return 1;
        } else if (r.precio.equals(this.precio)) {
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

    public void setPrecio(Integer precio) {
        this.precio = precio;
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
                ", precioFinal=" + precio +
                ", fechaInstalacion=" + fechaInstalacion +
                ", obra='" + obra + '\'' +
                '}';
    }
}
