package com.example.proyectofinalannedecor.Clases;


public class TipoTela {
    public int Id;
    public String Nombre;
    public String Color;
    public int Tipo;
    public String Codigo;

    public TipoTela(String nombre, String color, int tipo, String codigo) {
        Nombre = nombre;
        Color = color;
        Tipo = tipo;
        Codigo = codigo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    @Override
    public String toString() {
        return Nombre +"   "+ Color;
    }


}
