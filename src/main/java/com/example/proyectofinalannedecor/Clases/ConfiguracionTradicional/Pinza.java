package com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional;

public class Pinza {
    String nombre;
    int idPinza;

    public Pinza(String nombre, int idPinza) {
        this.nombre = nombre;
        this.idPinza = idPinza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPinza() {
        return idPinza;
    }

    public void setIdPinza(int idPinza) {
        this.idPinza = idPinza;
    }
}
