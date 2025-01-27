package com.example.proyectofinalannedecor.Dto;

import com.example.proyectofinalannedecor.Clases.Cliente;

import java.sql.Date;

public class VentaSimpleDto {
    private int id;
    private Cliente cliente;
    public java.sql.Date fecha;
    public Integer precio;
    public Date fechaInstalacion;
    public String obra;

    public VentaSimpleDto(int id, Cliente cliente, Date fecha, Integer precio, Date fechaInstalacion, String obra) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.precio = precio;
        this.fechaInstalacion = fechaInstalacion;
        this.obra = obra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
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
}
