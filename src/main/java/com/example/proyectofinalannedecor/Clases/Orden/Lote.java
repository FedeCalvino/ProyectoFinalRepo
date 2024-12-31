package com.example.proyectofinalannedecor.Clases.Orden;

import java.util.ArrayList;
import java.util.List;

public class Lote{

    public int Idlote;
    public String Estado;
    List<Orden> ordenes = new ArrayList();

    public Lote(int Idlote, String Estado){
        this.Idlote = Idlote;
        this.Estado = Estado;
    }

    public int getIdlote() {
        return Idlote;
    }

    public void setIdlote(int idlote) {
        Idlote = idlote;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }
}
