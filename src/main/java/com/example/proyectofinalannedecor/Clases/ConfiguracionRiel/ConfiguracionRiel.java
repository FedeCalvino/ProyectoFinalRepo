package com.example.proyectofinalannedecor.Clases.ConfiguracionRiel;

import java.util.List;

public class ConfiguracionRiel {
    List<LadoAcumula> ladoAcumula;
    List<TipoRiel> tipos;
    List<TipoBaston> tiposBastones;
    List<TipoSoportes> tiposSoportes;

    public ConfiguracionRiel(List<LadoAcumula> ladoAcumula, List<TipoRiel> tipos,List<TipoBaston> TiposBastones,List<TipoSoportes> TiposSoportes) {
        this.tiposSoportes = TiposSoportes;
        this.ladoAcumula = ladoAcumula;
        this.tipos = tipos;
        this.tiposBastones = TiposBastones;
    }

    public List<TipoSoportes> getTiposSoportes() {
        return tiposSoportes;
    }

    public void setTiposSoportes(List<TipoSoportes> tiposSoportes) {
        this.tiposSoportes = tiposSoportes;
    }

    public List<TipoBaston> getTiposBastones() {
        return tiposBastones;
    }

    public void setTiposBastones(List<TipoBaston> tiposBastones) {
        this.tiposBastones = tiposBastones;
    }

    public List<LadoAcumula> getLadoAcumula() {
        return ladoAcumula;
    }

    public void setLadoAcumula(List<LadoAcumula> ladoAcumula) {
        this.ladoAcumula = ladoAcumula;
    }

    public List<TipoRiel> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoRiel> tipos) {
        this.tipos = tipos;
    }
}
