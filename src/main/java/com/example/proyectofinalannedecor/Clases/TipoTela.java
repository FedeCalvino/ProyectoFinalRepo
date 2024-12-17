package com.example.proyectofinalannedecor.Clases;


public class TipoTela {
    private int Id;
    private String Nombre;
    private String Color;
    private int Tipo;
    private String Codigo;

    public TipoTela(String Nombre, String Color, int tipo, String codigo) {
        this.Nombre = Nombre;
        this.Color = Color;
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

    public void setColor(String Color) {
        this.Color = Color;
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
