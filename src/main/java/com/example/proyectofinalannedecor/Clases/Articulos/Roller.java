package com.example.proyectofinalannedecor.Clases.Articulos;

import com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.*;
import com.example.proyectofinalannedecor.Clases.Cortina;
import com.example.proyectofinalannedecor.Clases.Soporte;


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
    private Soporte soporte;

    public Roller(String NombreArt,String Ambiente, Float Alto, Float Ancho, int Idtela, TipoCadena cadena, Cano cano, Posicion Posicion, LadoCadena LadoCadena,String detalle,int numeroArticulo,Motor motor,Soporte soporte,String detalleInstalacion) {
        super(NombreArt,Alto, Ancho, Idtela, Ambiente,detalle,numeroArticulo,detalleInstalacion);
        this.soporte = soporte;
        this.TipoCadena = cadena;
        this.MotorRoller=motor;
        this.cano = cano;
        this.posicion = Posicion;
        this.ladoCadena = LadoCadena;
        setAltoTelaCadena();
        setAnchoTuboTela();
    }

    public Soporte getSoporte() {
        return soporte;
    }

    public void setSoporte(Soporte soporte) {
        this.soporte = soporte;
    }

    public com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.TipoCadena getTipoCadena() {
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
        if(this.MotorRoller.getIdMotor()!=1) {
            this.LargoCadena = (double) 0;
        }else{
            if (this.cano.getTipo() == 30) {
                this.LargoCadena = Math.round((this.getAlto() * 1.75) * 1000.0) / 1000.0;
            } else {
                this.LargoCadena = Math.round((this.getAlto() * 1.85) * 1000.0) / 1000.0;
            }
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

    /*public void setAnchoTuboTela() {
        if (this.MotorRoller.getIdMotor()!=0) {
            this.AnchoTela = Math.round((this.getAncho() - 0.030) * 1000.0) / 1000.0;
            this.AnchoTubo = Math.round((this.getAncho() - 0.025 - 0.030) * 1000.0) / 1000.0;
        } else {
            this.AnchoTela = Math.round((this.getAncho() - 0.040 - 0.030) * 1000.0) / 1000.0;
            this.AnchoTubo = Math.round((this.getAncho() - 0.040 - 0.030) * 1000.0) / 1000.0;
        }
    }*/

    public void setAnchoTuboTela() {
        // Formatea anchoAFAF a tres dígitos después de la coma
        String AnchoCortinaConPuntos = String.valueOf(this.getAncho());
        double AnchoCortinaDouble = Double.parseDouble(AnchoCortinaConPuntos);
        setAncho(Float.valueOf(String.format("%.3f", AnchoCortinaDouble)));
        double anchoCortina;
        double anchoCano;
        if(!this.Exterior) {
            if (this.MotorRoller.getIdMotor()!=1) {
                anchoCortina = AnchoCortinaDouble - 0.040;
                anchoCano = AnchoCortinaDouble - 0.035;
            }else {
                anchoCortina = AnchoCortinaDouble - 0.030;
                anchoCano = AnchoCortinaDouble - 0.025;
            }
        }else{
            anchoCortina = AnchoCortinaDouble - 0.080;
            anchoCano = AnchoCortinaDouble - 0.060;
        }
        // Para depuración, imprime los valores antes de formatearlos
        System.out.println("Ancho Cortina antes de formatear: " + anchoCortina);
        System.out.println("Ancho Caño antes de formatear: " + anchoCano);
        System.out.println("Ancho Caño despues de formatear: " + String.format("%.3f", anchoCano));
        System.out.println("Ancho Cortina despues de formatear: " + String.format("%.3f", anchoCortina));

        this.AnchoTela = Double.valueOf(String.format("%.3f", anchoCortina));
        this.AnchoTubo = Double.valueOf(String.format("%.3f", anchoCano));
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
                "Soporte=" + soporte +
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
