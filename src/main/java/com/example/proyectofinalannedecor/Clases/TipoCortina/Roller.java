package com.example.proyectofinalannedecor.Clases.TipoCortina;

import com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas.*;
import com.example.proyectofinalannedecor.Clases.Cortina;


public class Roller extends Cortina {

    public int IdRoller;
    public boolean Exterior;
    public Double LargoCadena;
    public Motor MotorRoller;
    public Double AnchoTela;
    public Double AnchoTubo;
    public Double AltoTela;
    public TipoCadena TipoCadena;
    public Cano cano;
    public Posicion posicion;
    public LadoCadena ladoCadena;
    private final byte trueBite = 1;
    private final byte falseBite = 0;

    public Roller(String NombreArt,String Ambiente, Float Alto, Float Ancho, int Idtela, TipoCadena cadena, Cano cano, Posicion Posicion, LadoCadena LadoCadena,String detalle,int numeroCortina,Motor motor) {
        super(NombreArt,Alto, Ancho, Idtela, Ambiente,detalle);
        this.TipoCadena = cadena;
        this.MotorRoller=motor;
        this.cano = cano;
        this.posicion = Posicion;
        this.ladoCadena = LadoCadena;
        setAltoTelaCadena();
        setAnchoTuboTela();
    }

    public com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas.TipoCadena getTipoCadena() {
        return TipoCadena;
    }

    public boolean isExterior() {
        return Exterior;
    }

    public void setExterior(boolean exterior) {
        Exterior = exterior;
    }

    public void setAltoTelaCadena() {
        this.AltoTela = Math.round((this.getAlto() + 0.30) * 1000.0) / 1000.0;

        if (this.cano.getTipo() == 30) {
            this.LargoCadena = Math.round((this.getAlto() * 1.75) * 1000.0) / 1000.0;
        } else {
            this.LargoCadena = Math.round((this.getAlto() * 1.85) * 1000.0) / 1000.0;
        }
    }

    public Motor getMotorRoller() {
        return MotorRoller;
    }

    public void setMotorRoller(Motor motorRoller) {
        MotorRoller = motorRoller;
    }

    public int getIdRoller() {
        return IdRoller;
    }

    public void setIdRoller(int idRoller) {
        IdRoller = idRoller;
    }

    public void setAnchoTuboTela() {
        if (this.MotorRoller.getIdMotor()!=0) {
            this.AnchoTela = Math.round((this.getAncho() - 0.030) * 1000.0) / 1000.0;
            this.AnchoTubo = Math.round((this.getAncho() - 0.025 - 0.030) * 1000.0) / 1000.0;
        } else {
            this.AnchoTela = Math.round((this.getAncho() - 0.040 - 0.030) * 1000.0) / 1000.0;
            this.AnchoTubo = Math.round((this.getAncho() - 0.040 - 0.030) * 1000.0) / 1000.0;
        }
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public double getLargoCadena() {
        return LargoCadena;
    }

    public TipoCadena isTipoCadena() {
        return TipoCadena;
    }

    public Cano getCano() {
        return cano;
    }

    public void setCano(Cano cano) {
        this.cano = cano;
    }

    public void setLargoCadena(double LargoCadena) {
        this.LargoCadena = LargoCadena;
    }

    public void setTipoCadena(TipoCadena Cadena) {
        this.TipoCadena = Cadena;
    }

    public void setCano(int cano) {
        this.cano.setTipo(cano);
    }

    public void setPosicion(String Posicion) {
        this.posicion.setPosicion(Posicion);
    }

    public void setLadoCadena(String LadoCadena) {
        ladoCadena.setLado(LadoCadena);
    }

    public LadoCadena getLadoCadena() {
        return ladoCadena;
    }

    public void setLadoCadena(LadoCadena ladoCadena) {
        this.ladoCadena = ladoCadena;
    }

    @Override
    public String toString() {
        return "Roller{" +
                "IdRoller=" + IdRoller +
                ", Exterior=" + Exterior +
                ", LargoCadena=" + LargoCadena +
                ", MotorRoller=" + MotorRoller.toString() +
                ", AnchoTela=" + AnchoTela +
                ", AnchoTubo=" + AnchoTubo +
                ", AltoTela=" + AltoTela +
                ", TipoCadena=" + TipoCadena.toString() +
                ", cano=" + cano.toString() +
                ", posicion=" + posicion.toString() +
                ", ladoCadena=" + ladoCadena.toString() +
                ", trueBite=" + trueBite +
                ", falseBite=" + falseBite +
                '}';
    }
}
