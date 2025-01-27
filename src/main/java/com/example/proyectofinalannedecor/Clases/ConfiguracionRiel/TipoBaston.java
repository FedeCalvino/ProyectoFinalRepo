package com.example.proyectofinalannedecor.Clases.ConfiguracionRiel;

public class TipoBaston {
    private int idTipoBaton;
    private String nombre;

    public TipoBaston(int idTipoBaton, String nombre) {
        this.idTipoBaton = idTipoBaton;
        this.nombre = nombre;
    }

    public int getIdTipoBaton() {
        return idTipoBaton;
    }

    public void setIdTipoBaton(int idTipoBaton) {
        this.idTipoBaton = idTipoBaton;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
