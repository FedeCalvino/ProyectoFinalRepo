package com.example.proyectofinalannedecor.Clases.ConfiguracionRiel;

public class TipoSoportes {
    private int IdTipoSoporte;
    private int Tipo;
    public String nombre;


    public TipoSoportes(int idTipoSoporte,int tipo, String nombre) {
        this.Tipo = tipo;
        this.IdTipoSoporte = idTipoSoporte;
        this.nombre = nombre;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoSoporte() {
        return IdTipoSoporte;
    }

    public void setIdTipoSoporte(int idTipoSoporte) {
        IdTipoSoporte = idTipoSoporte;
    }
}
