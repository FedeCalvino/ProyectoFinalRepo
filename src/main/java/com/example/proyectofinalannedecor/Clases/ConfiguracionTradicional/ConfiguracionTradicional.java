package com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionTradicional {
    private List<Ganchos> ganchos;
    private List<Pinza> pinzas;

    public ConfiguracionTradicional(List<Ganchos> ganchos, List<Pinza> pinzas) {
        this.ganchos = ganchos;
        this.pinzas = pinzas;
    }

    public List<Pinza> getPinzas() {
        return pinzas;
    }

    public void setPinzas(List<Pinza> pinzas) {
        this.pinzas = pinzas;
    }

    public List<Ganchos> getGanchos() {
        return ganchos;
    }

    public void setGanchos(List<Ganchos> ganchos) {
        this.ganchos = ganchos;
    }
}
