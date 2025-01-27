package com.example.proyectofinalannedecor.Dto;

import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.ConfiguracionRiel;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.ConfiguracionRoller;

public class ConfiguracionDto {
    private ConfiguracionRoller configuracionRoller;
    private ConfiguracionRiel configuracionRiel;

    public ConfiguracionDto() {

    }

    public ConfiguracionRoller getConfiguracionRoller() {
        return configuracionRoller;
    }

    public void setConfiguracionRoller(ConfiguracionRoller configuracionRoller) {
        this.configuracionRoller = configuracionRoller;
    }

    public ConfiguracionRiel getConfiguracionRiel() {
        return configuracionRiel;
    }

    public void setConfiguracionRiel(ConfiguracionRiel configuracionTradicional) {
        this.configuracionRiel = configuracionTradicional;
    }
}
