/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinalannedecor.Clases.TipoCortina;


import com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas.Motor;
import com.example.proyectofinalannedecor.Clases.Cortina;

public class Tradicional extends Cortina {

    public int CantidadPanos;
    public String AnchoDerecho;
    public String Acumula;
    public String Pinza;
    private byte trueBite = 1;
    private byte falseBite = 0;

    public Tradicional(String NombreArt, Float Alto, Float largo, Motor motorizada, int Idtela, int cantidadPanos, String Ambiente, String detalle, int numeroCortina, String AnchoDerecho, String Acumula, String pinza) {
        super(NombreArt,Alto, largo, Idtela,Ambiente,detalle);
        CantidadPanos = cantidadPanos;
        this.Pinza=pinza;
        this.AnchoDerecho = AnchoDerecho;
        this.Acumula = Acumula;
    }

    public void setCantidadPaños(int cantidadPaños) {
        CantidadPanos = cantidadPaños;
    }


    public void setAnchoDerecho(String anchoDerecho) {
        AnchoDerecho = anchoDerecho;
    }

    public void setAcumula(String acumula) {
        Acumula = acumula;
    }

    public int getCantidadPanos() {
        return CantidadPanos;
    }


    public String getAnchoDerecho() {
        return AnchoDerecho;
    }

    public String getAcumula() {
        return Acumula;
    }

    public String getPinza() {
        return Pinza;
    }

    public void setPinza(String pinza) {
        Pinza = pinza;
    }

    public void setCantidadPanos(int cantidadPanos) {
        CantidadPanos = cantidadPanos;
    }
}
