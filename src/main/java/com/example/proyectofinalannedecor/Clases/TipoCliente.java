package com.example.proyectofinalannedecor.Clases;

public class TipoCliente {
    String tipo;
    int IdTipoCliente;

    public TipoCliente(int idTipoCliente) {
        IdTipoCliente = idTipoCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdTipoCliente() {
        return IdTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        IdTipoCliente = idTipoCliente;
    }
}
