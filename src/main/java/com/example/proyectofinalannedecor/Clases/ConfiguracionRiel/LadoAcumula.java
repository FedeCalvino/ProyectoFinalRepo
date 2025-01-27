package com.example.proyectofinalannedecor.Clases.ConfiguracionRiel;

public class LadoAcumula {
    String nombre;
    int LadoAcumulaId;

    public LadoAcumula(int ladoAcumulaId) {
        LadoAcumulaId = ladoAcumulaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLadoAcumulaId() {
        return LadoAcumulaId;
    }

    public void setLadoAcumulaId(int ladoAcumulaId) {
        LadoAcumulaId = ladoAcumulaId;
    }

    @Override
    public String toString() {
        return "LadoAcumula{" +
                "nombre='" + nombre + '\'' +
                ", LadoAcumulaId=" + LadoAcumulaId +
                '}';
    }
}
