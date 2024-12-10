package com.example.proyectofinalannedecor.Clases.TipoCortina;

import com.example.proyectofinalannedecor.Clases.Cortina;


public class Roller extends Cortina {
    public int IdRoller;
    public boolean Exterior;
    public Double LargoCadena;
    public Double AnchoTela;
    public Double AnchoTubo;
    public Double AltoTela;
    public boolean CadenaMetalica;
    public int Tubo;
    public String Posicion;
    public String LadoCadena;
    private byte trueBite = 1;
    private byte falseBite = 0;

    public Roller(String NombreArt,String Ambiente, Float Alto, Float Ancho, Boolean motorizada, int Idtela, boolean cadenaMetalica, int Tubo, String Posicion, String LadoCadena,String detalle,int numeroCortina) {
        super(NombreArt,Alto, Ancho, motorizada, Idtela, Ambiente,detalle);
        this.CadenaMetalica = cadenaMetalica;
        this.Tubo = Tubo;
        this.Posicion = Posicion;
        this.LadoCadena = LadoCadena;
        setAltoTelaCadena();
        setAnchoTuboTela();
    }

    public boolean isExterior() {
        return Exterior;
    }

    public void setExterior(boolean exterior) {
        Exterior = exterior;
    }

    public void setAltoTelaCadena() {
        this.AltoTela = Math.round((this.getAlto() + 0.30) * 1000.0) / 1000.0;

        if (this.Tubo == 30) {
            this.LargoCadena = Math.round((this.getAlto() * 1.75) * 1000.0) / 1000.0;
        } else {
            this.LargoCadena = Math.round((this.getAlto() * 1.85) * 1000.0) / 1000.0;
        }
    }


    public int getIdRoller() {
        return IdRoller;
    }

    public void setIdRoller(int idRoller) {
        IdRoller = idRoller;
    }

    public void setAnchoTuboTela() {
        if (!this.getMotorizada()) {
            this.AnchoTela = Math.round((this.getAncho() - 0.030) * 1000.0) / 1000.0;
            this.AnchoTubo = Math.round((this.getAncho() - 0.025 - 0.030) * 1000.0) / 1000.0;
        } else {
            this.AnchoTela = Math.round((this.getAncho() - 0.040 - 0.030) * 1000.0) / 1000.0;
            this.AnchoTubo = Math.round((this.getAncho() - 0.040 - 0.030) * 1000.0) / 1000.0;
        }
    }


    public double getLargoCadena() {
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

    public void setLargoCadena(double LargoCadena) {
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
