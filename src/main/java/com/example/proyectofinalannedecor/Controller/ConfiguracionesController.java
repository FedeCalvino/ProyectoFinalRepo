package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas.ConfiguracionRoller;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCliente;
import com.example.proyectofinalannedecor.Service.ConfigService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Conf")
public class ConfiguracionesController {

    private ConfigService ConfService;

    public ConfiguracionesController(ConfigService cs) {
        this.ConfService = cs;
    }

    @GetMapping
    public CustomResponseEntity<ConfiguracionRoller> findAll() {
        CustomResponseEntity<ConfiguracionRoller> response = ConfService.findAllConfigRoller();
        return response;
    }

    @GetMapping("/TiposCli")
    public CustomResponseEntity<List<TipoCliente>> findAllTiposCli() {
        CustomResponseEntity<List<TipoCliente>> response = ConfService.findAllTiposClientes();
        return response;
    }
}
