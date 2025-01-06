package com.example.proyectofinalannedecor.Clases;

import com.example.proyectofinalannedecor.Clases.Orden.Lote;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Tradicional;
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
})
public class Articulo{
    private int IdArticulo;
    private String codigoBarras;
    private String nombre;
    public Articulo(String nombre) {
        this.nombre = nombre;
        this.codigoBarras = generarCodigoBarras();
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

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.IdArticulo = idArticulo;
        this.codigoBarras = generarCodigoBarras();
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
                "codigoArticulo=" + IdArticulo +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

