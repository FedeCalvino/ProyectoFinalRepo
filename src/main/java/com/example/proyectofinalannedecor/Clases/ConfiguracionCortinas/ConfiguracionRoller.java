package com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas;

import java.util.List;

public class ConfiguracionRoller {
    List<Posicion> Posiciones;
    List<Cano> Canos;
    List<Motor> Motores;
    List<LadoCadena> LadosCadena;
    List<TipoCadena> TiposCadena;

    public ConfiguracionRoller(List<Posicion> posiciones, List<Cano> canos, List<Motor> motores, List<LadoCadena> ladosCadena, List<TipoCadena> tiposCadena) {
        Posiciones = posiciones;
        Canos = canos;
        Motores = motores;
        LadosCadena = ladosCadena;
        TiposCadena = tiposCadena;
    }

    public List<TipoCadena> getTiposCadena() {
        return TiposCadena;
    }

    public void setTiposCadena(List<TipoCadena> tiposCadena) {
        TiposCadena = tiposCadena;
    }

    public List<LadoCadena> getLadosCadena() {
        return LadosCadena;
    }

    public void setLadosCadena(List<LadoCadena> ladosCadena) {
        LadosCadena = ladosCadena;
    }

    public List<Motor> getMotores() {
        return Motores;
    }

    public void setMotores(List<Motor> motores) {
        Motores = motores;
    }

    public List<Cano> getCanos() {
        return Canos;
    }

    public void setCanos(List<Cano> canos) {
        Canos = canos;
    }

    public List<Posicion> getPosiciones() {
        return Posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        Posiciones = posiciones;
    }
}
