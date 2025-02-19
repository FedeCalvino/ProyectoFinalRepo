package com.example.proyectofinalannedecor.Clases.Articulos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoArticulo"
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = Roller.class, name = "roller"),
        @JsonSubTypes.Type(value = Tradicional.class, name = "tradicional"),
        @JsonSubTypes.Type(value = Riel.class, name = "riel"),
})

public class Articulo{

    private int numeroArticulo;
    private int IdVenta;
    public int IdArticulo;
    private String codigoBarras;
    private String nombre;
    private String detalleInstalacion;

    public Articulo(String nombre,int numeroArticulo,String detalleInstalacion) {
        this.detalleInstalacion = detalleInstalacion;
        this.numeroArticulo=numeroArticulo;
        this.nombre = nombre;
        this.codigoBarras = generarCodigoBarras();
    }

    public String getDetalleInstalacion() {
        return detalleInstalacion;
    }

    public void setDetalleInstalacion(String detalleInstalacion) {
        this.detalleInstalacion = detalleInstalacion;
    }

    private String generarCodigoBarras() {
        String uniqueID = UUID.randomUUID().toString().substring(0, 8);
        return "ART-" + IdArticulo + "-" + uniqueID;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int idVenta) {
        IdVenta = idVenta;
    }

    public int getNumeroArticulo() {
        return numeroArticulo;
    }

    public void setNumeroArticulo(int numeroArticulo) {
        this.numeroArticulo = numeroArticulo;
    }

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.IdArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "IdArticulo=" + IdArticulo +
                "codigoArticulo=" + IdArticulo +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

