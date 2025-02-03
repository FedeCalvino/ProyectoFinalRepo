package com.example.proyectofinalannedecor.Clases.ConfiguracionRiel;

public class Bastones {
    private int IdBastones;
    private int Idtipo;
    private int cantidad;
    private String nombre;

    public Bastones(int IdBastones,int Idtipo, int cantidad) {
        this.IdBastones = IdBastones;
        this.Idtipo = Idtipo;
        this.cantidad = cantidad;
    }

    public int getIdBastones() {
        return IdBastones;
    }

    public void setIdBastones(int idBatones) {
        IdBastones = idBatones;
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
                "IdBatones=" + IdBastones +
                ", Idtipo=" + Idtipo +
                ", cantidad=" + cantidad +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
