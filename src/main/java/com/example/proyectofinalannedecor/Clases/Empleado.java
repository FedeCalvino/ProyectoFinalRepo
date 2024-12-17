package com.example.proyectofinalannedecor.Clases;

public class Empleado {
    String nombre;
    int IdEmpleado;


    public Empleado(String nombre, int idEmpleado) {
        this.nombre = nombre;
        IdEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
    }
}
