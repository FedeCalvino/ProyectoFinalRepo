package com.example.proyectofinalannedecor.Clases;

import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int id;
    private Cliente cliente;
    private List<Articulo> Articulos = new ArrayList<>();
    public java.sql.Date fecha;
    public Integer precio;
    public Date fechaInstalacion;
    public String obra;

    public Venta(Integer Id,Cliente cliente, List<Articulo> Articulos, Date fecha, Date fechaInstalacion, Integer precio, String obra) {
        this.id = Id;
        this.cliente = cliente;
        this.Articulos = Articulos;
        this.fecha = fecha;
        this.fechaInstalacion = fechaInstalacion;
        this.obra = obra;
        this.precio = precio;
    }

    public void setArticulos(List<Articulo> articulos) {
        Articulos = articulos;
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

    public void setRoller(ArrayList<Articulo> rollers) {
        this.Articulos = rollers;
    }


    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public List<Articulo> getListaArticulos() {
        return this.Articulos;
    }

    public void addArticulo(Articulo r) {
        this.Articulos.add(r);
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", cortinas=" + Articulos +
                ", fecha=" + fecha +
                ", precioFinal=" + precio +
                ", fechaInstalacion=" + fechaInstalacion +
                ", obra='" + obra + '\'' +
                '}';
    }
}
