/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyectofinalannedecor.Clases.Articulos;


import com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.Motor;
import com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Ganchos;
import com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Pinza;
import com.example.proyectofinalannedecor.Clases.Cortina;

import java.math.BigDecimal;

public class Tradicional extends Cortina {
    public int TradicionalId;

    public int CantidadPanos;
    public int CantidadAltos;

    public BigDecimal AnchoDerecho;
    public BigDecimal AltoDerecho;

    public Pinza Pinza;
    private Ganchos Ganchos;


    private byte trueBite = 1;
    private byte falseBite = 0;

    public Tradicional(String NombreArt, Float Alto, Float Ancho, Ganchos ganchos, int Idtela, int cantidadPanos,int CantidadAltos, String Ambiente, String detalle, int numeroArticulo, BigDecimal AnchoDerecho, BigDecimal AltoDerecho, Pinza pinza,String detalleInstalacion) {
        super(NombreArt,Alto, Ancho, Idtela,Ambiente,detalle,numeroArticulo,detalleInstalacion);
        CantidadPanos = cantidadPanos;
        this.CantidadAltos = CantidadAltos;
        this.Pinza=pinza;
        this.AnchoDerecho = AnchoDerecho;
        this.AltoDerecho = AltoDerecho;
        this.Ganchos = ganchos;
    }

    public int getCantidadPanos() {
        return CantidadPanos;
    }

    public void setCantidadPanos(int cantidadPanos) {
        CantidadPanos = cantidadPanos;
    }

    public int getCantidadAltos() {
        return CantidadAltos;
    }

    public void setCantidadAltos(int cantidadAltos) {
        CantidadAltos = cantidadAltos;
    }

    public BigDecimal getAnchoDerecho() {
        return AnchoDerecho;
    }

    public int getTradicionalId() {
        return TradicionalId;
    }

    public void setTradicionalId(int tradicionalId) {
        TradicionalId = tradicionalId;
    }

    public void setAnchoDerecho(BigDecimal anchoDerecho) {
        AnchoDerecho = anchoDerecho;
    }

    public BigDecimal getAltoDerecho() {
        return AltoDerecho;
    }

    public void setAltoDerecho(BigDecimal altoDerecho) {
        AltoDerecho = altoDerecho;
    }

    public com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Pinza getPinza() {
        return Pinza;
    }

    public void setPinza(com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Pinza pinza) {
        Pinza = pinza;
    }

    public com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Ganchos getGanchos() {
        return Ganchos;
    }

    public void setGanchos(com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.Ganchos ganchos) {
        Ganchos = ganchos;
    }
}
