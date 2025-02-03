package com.example.proyectofinalannedecor.Clases.Articulos;

import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.Bastones;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.LadoAcumula;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.TipoRiel;
import com.example.proyectofinalannedecor.Clases.Soporte;

public class Riel extends Articulo {
    private int idRiel;
    private int ArticuloId;
    private String Ambiente;
    private Float Ancho;
    private TipoRiel tipoRiel;
    private LadoAcumula ladoAcumula;
    private String detalle;
    private Bastones bastones;
    private Soporte soportes;

    public Riel(String nombre, String ambiente, Float ancho, TipoRiel tipoRiel, LadoAcumula ladoAcumula, String detalle, Bastones bastones, Soporte soportes,int numeroArticulo,String detalleInstalacion) {
        super(nombre,numeroArticulo,detalleInstalacion);
        this.Ambiente = ambiente;
        Ancho = ancho;
        this.tipoRiel = tipoRiel;
        this.ladoAcumula = ladoAcumula;
        this.detalle = detalle;
        this.bastones = bastones;
        this.soportes = soportes;
    }

    public int getIdRiel() {
        return idRiel;
    }

    public void setIdRiel(int idRiel) {
        this.idRiel = idRiel;
    }

    public String getAmbiente() {
        return Ambiente;
    }

    public void setAmbiente(String ambiente) {
        Ambiente = ambiente;
    }

    public int getArticuloId() {
        return ArticuloId;
    }

    public void setArticuloId(int articuloId) {
        ArticuloId = articuloId;
    }

    public Float getAncho() {
        return Ancho;
    }

    public void setAncho(Float ancho) {
        Ancho = ancho;
    }

    public TipoRiel getTipoRiel() {
        return tipoRiel;
    }

    public void setTipoRiel(TipoRiel tipoRiel) {
        this.tipoRiel = tipoRiel;
    }

    public LadoAcumula getLadoAcumula() {
        return ladoAcumula;
    }

    public void setLadoAcumula(LadoAcumula ladoAcumula) {
        this.ladoAcumula = ladoAcumula;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Bastones getBastones() {
        return bastones;
    }

    public void setBastones(Bastones bastones) {
        this.bastones = bastones;
    }

    public Soporte getSoportes() {
        return soportes;
    }

    public void setSoportes(Soporte soportes) {
        this.soportes = soportes;
    }

    @Override
    public String toString() {
        return "Riel{" +
                "idRiel=" + idRiel +
                ", ArticuloId=" + ArticuloId +
                ", Ambiente='" + Ambiente + '\'' +
                ", Ancho=" + Ancho +
                ", tipoRiel=" + tipoRiel +
                ", ladoAcumula=" + ladoAcumula +
                ", detalle='" + detalle + '\'' +
                "detalle instalacion="+getDetalleInstalacion()+"/"+
                ", bastones=" + bastones +
                ", soportes=" + soportes +
                ", IdArticulo=" + IdArticulo +
                '}';
    }
}