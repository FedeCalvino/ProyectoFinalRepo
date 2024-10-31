package com.example.proyectofinalannedecor.Dto;


import java.util.Date;

public class DtoVenta {
    public String NombreCliente;

    public int IdCliente;
    public int IdVenata;
    public Date FechaVenta;
    public String Obra;
    public int Monto;
    public Date DiaInstalacion;
    public int CantidadaRollers;
    public int CantidadaTradicionales;
    public int CantidadaRieles;

    public DtoVenta() {

    }

    public void setCantidadaTradicionales(int cantidadaTradicionales) {
        CantidadaTradicionales = cantidadaTradicionales;
    }

    public void setCantidadaRollers(int cantidadaRollers) {
        CantidadaRollers = cantidadaRollers;
    }

    public void setCantidadaRieles(int cantidadaRieles) {
        CantidadaRieles = cantidadaRieles;
    }

    public void setDiaInstalacion(Date diaInstalacion) {
        if (diaInstalacion != null)
        DiaInstalacion = diaInstalacion;
    }

    public void setObra(String obra) {
        Obra = obra;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public void setIdVenata(int idVenata) {
        IdVenata = idVenata;
    }

    public void setMonto(int monto) {
        Monto = monto;
    }

    public void setFechaVenta(Date fechaVenta) {
        FechaVenta = fechaVenta;
    }

}
