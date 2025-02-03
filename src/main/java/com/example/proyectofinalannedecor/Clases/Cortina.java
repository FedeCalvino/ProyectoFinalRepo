package com.example.proyectofinalannedecor.Clases;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;

import java.util.Date;

public class Cortina extends Articulo {

    public int IdCortina;
    Float Alto;
    public int IdTipoTela;
    public String Ambiente;
    Float Ancho;
    String Detalle;
    TipoTela tela;


    public void setTela(TipoTela tela) {
        this.tela = tela;
    }

    public String getAmbiente() {
        return Ambiente;
    }

    public Cortina(String nombre,Float Alto, Float Ancho, int Idtela, String Ambiente,String detalle,int numeroArticulo,String detalleInstalacion) {
        super(nombre,numeroArticulo,detalleInstalacion);
        this.IdTipoTela=Idtela;
        this.Alto = Alto;
        this.Ancho = Ancho;
        this.Ambiente = Ambiente;
        this.Detalle = detalle;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }


    public int GetTipoTelaId(){
        return IdTipoTela;
    }

    public int GetEstadoCortinaId(){
        return IdTipoTela;
    }

    public int getId() {
        return IdCortina;
    }

    public void setId(int Id) {
        this.IdCortina = Id;
    }

    public Float getAlto() {
        return Alto;
    }

    public void setAlto(Float Alto) {
        this.Alto = Alto;
    }

    public Float getAncho() {
        return Ancho;
    }

    public void setAncho(Float ancho) {
        this.Ancho = ancho;
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

    @Override
    public String toString() {
        return "Cortina{" +
                "IdCortina=" + IdCortina +
                ", Alto=" + Alto +
                ", IdTipoTela=" + IdTipoTela +
                ", Ambiente='" + Ambiente + '\'' +
                ", Ancho=" + Ancho +
                ", Detalle='" + Detalle + '\'' +
                ", tela=" + tela +
                ", IdArticulo=" + IdArticulo +
                '}';
    }
}
