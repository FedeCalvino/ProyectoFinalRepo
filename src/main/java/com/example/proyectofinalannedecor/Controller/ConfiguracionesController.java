package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.ConfiguracionRiel.ConfiguracionRiel;
import com.example.proyectofinalannedecor.Clases.ConfiguracionRoller.ConfiguracionRoller;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCliente;
import com.example.proyectofinalannedecor.Dto.ConfiguracionDto;
import com.example.proyectofinalannedecor.Service.ConfigService;
import org.springframework.http.HttpStatus;
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
    public CustomResponseEntity<ConfiguracionDto> findAll() {
        CustomResponseEntity<ConfiguracionDto> response = new CustomResponseEntity<>();
        ConfiguracionDto configuracionDto = new ConfiguracionDto();
        CustomResponseEntity<ConfiguracionRoller> responseRoller = ConfService.findAllConfigRoller();
        CustomResponseEntity<ConfiguracionRiel> responseTradicional = ConfService.findAllConfigTradicional();

        configuracionDto.setConfiguracionRoller(responseRoller.getBody());
        configuracionDto.setConfiguracionRiel(responseTradicional.getBody());
        response.setBody(configuracionDto);
        response.setStatus(HttpStatus.OK);
        return response;
    }

    @GetMapping("/TiposCli")
    public CustomResponseEntity<List<TipoCliente>> findAllTiposCli() {
        CustomResponseEntity<List<TipoCliente>> response = ConfService.findAllTiposClientes();
        return response;
    }
}
