package com.example.proyectofinalannedecor.Clases;

import java.util.Date;

public class Cortina {
    public int Id;
    public int NumeroCortina;
    String Alto;
    public int IdTipoTela;
    public String Ambiente;
    String Ancho;
    String Detalle;
    TipoTela tela;
    Boolean motorizada;


    public void setTela(TipoTela tela) {
        this.tela = tela;
    }

    public String getAmbiente() {
        return Ambiente;
    }

    public Cortina(String Alto, String Ancho, Boolean motorizada, int Idtela, String Ambiente,String detalle,int numeroCortina) {
        this.IdTipoTela=Idtela;
        this.Alto = Alto;
        this.Ancho = Ancho;
        this.Ambiente = Ambiente;
        this.NumeroCortina = numeroCortina;
        this.motorizada = motorizada;
        this.Detalle = detalle;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public int getNumeroCortina() {
        return NumeroCortina;
    }

    public void setNumeroCortina(int numeroCortina) {
        NumeroCortina = numeroCortina;
    }


    public int GetTipoTelaId(){
        return IdTipoTela;
    }

    public int GetEstadoCortinaId(){
        return IdTipoTela;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAlto() {
        return Alto;
    }

    public void setAlto(String Alto) {
        this.Alto = Alto;
    }

    public String getAncho() {
        return Ancho;
    }

    public void setAncho(String ancho) {
        this.Ancho = ancho;
    }

    public Boolean getMotorizada() {
        return motorizada;
    }

    public void setMotorizada(Boolean motorizada) {
        this.motorizada = motorizada;
    }





    public String GetNombreTela(){
        return tela.getNombre();
    }


    public static class Orden {
        int IdOrden;
        Cortina cortina;
        String Estado;
        Date Finalizado;

        public Orden(Cortina cortina) {
            this.cortina = cortina;
        }

        public int getIdOrden() {
            return IdOrden;
        }

        public void setIdOrden(int idOrden) {
            IdOrden = idOrden;
        }

        public Cortina getCortina() {
            return cortina;
        }

        public void setCortina(Cortina cortina) {
            this.cortina = cortina;
        }

        public Date getFinalizado() {
            return Finalizado;
        }

        public void setFinalizado(Date finalizado) {
            Finalizado = finalizado;
        }

        public String getEstado() {
            return Estado;
        }

        public void setEstado(String estado) {
            Estado = estado;
        }
    }
}
