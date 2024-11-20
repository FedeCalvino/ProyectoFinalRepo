package com.example.proyectofinalannedecor.Clases.TipoCortina;

import com.example.proyectofinalannedecor.Clases.Cortina;


public class Roller extends Cortina {

    public String LargoCadena;
    public boolean CadenaMetalica;
    public int Tubo;
    public String Posicion;
    public String LadoCadena;
    private byte trueBite = 1;
    private byte falseBite = 0;

    public Roller(String NombreArt,String Ambiente, String Alto, String Ancho, Boolean motorizada, int Idtela, String largoCadena, boolean cadenaMetalica, int Tubo, String Posicion, String LadoCadena,String detalle,int numeroCortina) {
        super(NombreArt,Alto, Ancho, motorizada, Idtela, Ambiente,detalle);
        this.LargoCadena = largoCadena;
        this.CadenaMetalica = cadenaMetalica;
        this.Tubo = Tubo;
        this.Posicion = Posicion;
        this.LadoCadena = LadoCadena;
    }

    public String getLargoCadena() {
        return LargoCadena;
    }

    public boolean isCadenaMetalica() {
        return CadenaMetalica;
    }

    public Byte isCadenaMetalicaByte() {
        return CadenaMetalica ? trueBite : falseBite;
    }

    public int getTubo() {
        return Tubo;
    }

    public void setLargoCadena(String LargoCadena) {
        this.LargoCadena = LargoCadena;
    }

    public void setCadenaMetalica(boolean CadenaMetalica) {
        this.CadenaMetalica = CadenaMetalica;
    }

    public void setTubo(int Tubo) {
        this.Tubo = Tubo;
    }

    public String getPosicion() {
        return Posicion;
    }

    public void setPosicion(String Posicion) {
        this.Posicion = Posicion;
    }

    public void setLadoCadena(String LadoCadena) {
        this.LadoCadena = LadoCadena;
    }

    public String getLadoCadena() {
        return LadoCadena;
    }
    @Override
    public String toString() {
        return "Roller {" +
                "Alto='" + getAlto() + '\'' +
                ", Ancho='" + getAncho() + '\'' +
                ", IdTipoTela=" + GetTipoTelaId() +
                ", Ambiente='" + getAmbiente() + '\'' +
                ", Motorizada=" + getMotorizada() +
                ", LargoCadena='" + LargoCadena + '\'' +
                ", CadenaMetalica=" + CadenaMetalica +
                ", Tubo=" + Tubo +
                ", Posicion='" + Posicion + '\'' +
                ", LadoCadena='" + LadoCadena + '\'' +
                '}';
    }
}
