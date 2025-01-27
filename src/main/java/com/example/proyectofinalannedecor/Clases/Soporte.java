package com.example.proyectofinalannedecor.Clases;

public class Soporte {
    private int idSoporte;
    private String nombre;
    private int idTipo;
    private int Cantidad;

    public Soporte(int idTipo, int cantidad) {
        this.idTipo = idTipo;
        Cantidad = cantidad;
    }

    public int getIdSoporte() {
        return idSoporte;
    }

    public void setIdSoporte(int idSoporte) {
        this.idSoporte = idSoporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Soporte{" +
                "idSoporte=" + idSoporte +
                ", nombre='" + nombre + '\'' +
                ", idTipo=" + idTipo +
                ", Cantidad=" + Cantidad +
                '}';
    }
}
