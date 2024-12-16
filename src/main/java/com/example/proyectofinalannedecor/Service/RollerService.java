package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulo;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Conexion.CortinaConexion;
import com.example.proyectofinalannedecor.Conexion.RollerConexion;
import com.example.proyectofinalannedecor.Conexion.VentasConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RollerService implements IService<Roller>{

    private static final RollerConexion rollerConexion= new RollerConexion();
    private static final ConfigService configService= new ConfigService();

    @Override
    public CustomResponseEntity<List<Roller>> findAll() {
        return rollerConexion.findAll();
    }

    @Override
    public CustomResponseEntity<Roller> Save(Roller venta) {
        return rollerConexion.save(venta);
    }
    public CustomResponseEntity<Roller> findRollerArticulo(Articulo articulo) {
        CustomResponseEntity<Roller> response = new CustomResponseEntity<>();
        Roller roll =  rollerConexion.findRollerArticulo(articulo).getBody();
        System.out.println(roll);
        roll.setCano(configService.findCanobyId(roll.getCano().getId()).getBody());
        roll.setLadoCadena(configService.findLadoCadenabyId(roll.getLadoCadena().getLadoId()).getBody());
        roll.setMotorRoller(configService.findMotorbyId(roll.getMotorRoller().getIdMotor()).getBody());
        roll.setPosicion(configService.findPosbyId(roll.getPosicion().getPosicionId()).getBody());
        roll.setTipoCadena(configService.findTipoCadenabyId(roll.getTipoCadena().getIdTipoCadena()).getBody());

        response.setBody(roll);
        return response;
    }

    @Override
    public CustomResponseEntity<Roller> update(Roller venta) {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Roller> findById(int id) {
        return null;
    }
}
