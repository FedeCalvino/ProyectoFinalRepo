
package com.example.proyectofinalannedecor.Clases;

import java.util.Date;
import java.util.List;


public class Instalacion {

    int Id;
    Venta venta;
    int IdVenta;
    String Start;
    String End;
    String Aclaraciones;
    String title;

    public Instalacion(int Idventa, String start, String aclaraciones, String end, String title) {
        this.IdVenta = Idventa;
        Start = start;
        Aclaraciones = aclaraciones;
        End = end;
        this.title = title;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int idVenta) {
        IdVenta = idVenta;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getAclaraciones() {
        return Aclaraciones;
    }

    public void setAclaraciones(String aclaraciones) {
        Aclaraciones = aclaraciones;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Instalacion{" +
                "Id=" + Id +
                "Idventa=" + IdVenta +
                ", Start='" + Start + '\'' +
                ", End='" + End + '\'' +
                ", Aclaraciones='" + Aclaraciones + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
