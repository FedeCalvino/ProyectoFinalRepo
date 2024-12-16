package com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas;

public class LadoCadena {
    String Lado;
    int LadoId;

    public LadoCadena(int lado) {
        LadoId = lado;
    }

    public String getLado() {
        return Lado;
    }

    public void setLado(String lado) {
        Lado = lado;
    }

    public int getLadoId() {
        return LadoId;
    }

    public void setLadoId(int ladoId) {
        LadoId = ladoId;
    }

    @Override
    public String toString() {
        return "LadoCadena{" +
                "Lado='" + Lado + '\'' +
                ", LadoId=" + LadoId +
                '}';
    }
}
