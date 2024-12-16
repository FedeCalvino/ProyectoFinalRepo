package com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas;

public class Posicion {
    String posicion;
    int posicionId;

    public Posicion(int posicionId) {
        this.posicionId = posicionId;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(int posicionId) {
        this.posicionId = posicionId;
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "posicion='" + posicion + '\'' +
                ", posicionId=" + posicionId +
                '}';
    }
}
