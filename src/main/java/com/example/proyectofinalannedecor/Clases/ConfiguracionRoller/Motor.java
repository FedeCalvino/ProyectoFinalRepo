package com.example.proyectofinalannedecor.Clases.ConfiguracionRoller;

public class Motor {
    String Nombre;
    int IdMotor;
    int Tipo;

    public Motor(int idMotor) {
        IdMotor = idMotor;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getIdMotor() {
        return IdMotor;
    }

    public void setIdMotor(int idMotor) {
        IdMotor = idMotor;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "Nombre='" + Nombre + '\'' +
                ", IdMotor=" + IdMotor +
                ", Tipo=" + Tipo +
                '}';
    }
}
