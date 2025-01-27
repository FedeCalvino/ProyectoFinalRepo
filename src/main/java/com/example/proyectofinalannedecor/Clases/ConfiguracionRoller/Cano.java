package com.example.proyectofinalannedecor.Clases.ConfiguracionRoller;

public class Cano {
    int Tipo;
    int Id;

    public Cano(int id) {
        Id = id;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Cano{" +
                "Tipo=" + Tipo +
                ", Id=" + Id +
                '}';
    }
}
