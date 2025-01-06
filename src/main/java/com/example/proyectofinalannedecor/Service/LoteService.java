package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.Lote;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Clases.Orden.PasoOrden;
import com.example.proyectofinalannedecor.Conexion.LoteConexion;
import com.example.proyectofinalannedecor.Conexion.OrdenConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteService implements IService<Lote> {

    private static final LoteConexion LConexion= new LoteConexion();

    @Override
    public CustomResponseEntity<List<Lote>> findAll() {
        return LConexion.findAll();
    }

    @Override
    public CustomResponseEntity<Lote> Save(Lote lote) {
        CustomResponseEntity<Lote> response = new CustomResponseEntity<>();
        Lote Savedlote = LConexion.save(lote).getBody();
        for (PasoOrden orden : lote.getOrdenes()) {
            LConexion.savePasoLote(orden,Savedlote);
        }
        response.setBody(Savedlote);
        return response;
    }

    @Override
    public CustomResponseEntity<Lote> update(Lote Clase) {
        return LConexion.update(Clase);
    }

    @Override
    public CustomResponseEntity<Lote> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Lote> findById(int id) {
        return null;
    }

    public CustomResponseEntity<List<Lote>> findAllFecha(String fecha) {
        return LConexion.findAllFECHA(fecha);
    }
}
