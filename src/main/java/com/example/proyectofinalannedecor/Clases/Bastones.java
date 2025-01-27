package com.example.proyectofinalannedecor.Clases;

public class Bastones {
    private int IdBatones;
    private int Idtipo;
    private int cantidad;
    private String nombre;

    public Bastones(int Idtipo, int cantidad) {
        this.Idtipo = Idtipo;
        this.cantidad = cantidad;
    }

    public int getIdBatones() {
        return IdBatones;
    }

    public void setIdBatones(int idBatones) {
        IdBatones = idBatones;
    }

    public int getIdtipo() {
        return Idtipo;
    }

    public void setIdtipo(int id) {
        Idtipo = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Bastones{" +
                "IdBatones=" + IdBatones +
                ", Idtipo=" + Idtipo +
                ", cantidad=" + cantidad +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
