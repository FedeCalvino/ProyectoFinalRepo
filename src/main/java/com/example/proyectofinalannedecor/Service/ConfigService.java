package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas.*;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCliente;
import com.example.proyectofinalannedecor.Conexion.ConfiguracionesConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {
    private static final ConfiguracionesConexion rollerConfConexion= new ConfiguracionesConexion();

    public CustomResponseEntity<ConfiguracionRoller> findAllConfigRoller() {
        CustomResponseEntity<ConfiguracionRoller> response = rollerConfConexion.findAllConfRollers();
        return response;
    }

    public CustomResponseEntity<List<TipoCliente>> findAllTiposClientes() {
        CustomResponseEntity<List<TipoCliente>> response = rollerConfConexion.GetTiposClientes();
        return response;
    }
    public CustomResponseEntity<Cano> findCanobyId(int cano) {
        CustomResponseEntity<Cano> response = new CustomResponseEntity<>();
        Cano Rescano = rollerConfConexion.GetCanoPorId(cano);
        response.setBody(Rescano);
        return response;
    }
    public CustomResponseEntity<Motor> findMotorbyId(int motor) {
        CustomResponseEntity<Motor> response = new CustomResponseEntity<>();
        Motor Resmotor = rollerConfConexion.GetMotorePorId(motor);
        response.setBody(Resmotor);
        return response;
    }
    public CustomResponseEntity<Posicion> findPosbyId(int pos) {
        CustomResponseEntity<Posicion> response = new CustomResponseEntity<>();
        Posicion Respos = rollerConfConexion.GetPosicionPorId(pos);
        response.setBody(Respos);
        return response;
    }
    public CustomResponseEntity<TipoCadena> findTipoCadenabyId(int TipoCadenaId) {
        CustomResponseEntity<TipoCadena> response = new CustomResponseEntity<>();
        TipoCadena ResTipoCadena = rollerConfConexion.GetTipoCadenaPorId(TipoCadenaId);
        response.setBody(ResTipoCadena);
        return response;
    }
    public CustomResponseEntity<LadoCadena> findLadoCadenabyId(int LadoCadenaId) {
        CustomResponseEntity<LadoCadena> response = new CustomResponseEntity<>();
        LadoCadena ResLadoCadena = rollerConfConexion.GetLadoCadenaPorId(LadoCadenaId);
        response.setBody(ResLadoCadena);
        return response;
    }
}
