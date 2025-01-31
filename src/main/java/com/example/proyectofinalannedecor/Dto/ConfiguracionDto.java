package com.example.proyectofinalannedecor.Dto;

import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.ConfiguracionRiel;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.ConfiguracionRoller;
import com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.ConfiguracionTradicional;

public class ConfiguracionDto {

    private ConfiguracionRoller configuracionRoller;
    private ConfiguracionRiel configuracionRiel;
    private ConfiguracionTradicional ConfiguracionTradicional;

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

    public com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.ConfiguracionTradicional getConfiguracionTradicional() {
        return ConfiguracionTradicional;
    }

    public void setConfiguracionTradicional(com.example.proyectofinalannedecor.Clases.ConfiguracionTradicional.ConfiguracionTradicional configuracionTradicional) {
        ConfiguracionTradicional = configuracionTradicional;
    }
}
