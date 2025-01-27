package com.example.proyectofinalannedecor.Clases.ConfiguracionRiel;

public class TipoRiel {
    String Nombre;
    int TipoId;

    public TipoRiel(int tipoId) {
        TipoId = tipoId;
    }

    public String getTipo() {
        return Nombre;
    }

    public void setTipo(String nombre) {
        Nombre = nombre;
    }

    public int getTipoId() {
        return TipoId;
    }

    public void setTipoId(int tipoId) {
        TipoId = tipoId;
    }

    @Override
    public String toString() {
        return "TipoRiel{" +
                "Nombre='" + Nombre + '\'' +
                ", TipoId=" + TipoId +
                '}';
    }
}
