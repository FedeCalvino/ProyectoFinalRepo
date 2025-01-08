package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.Lote;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Clases.Orden.PasoOrden;
import com.example.proyectofinalannedecor.Conexion.LoteConexion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteService implements IService<Lote> {

    private static final LoteConexion LConexion= new LoteConexion();
    private static final OrderService OService= new OrderService();

    @Override
    public CustomResponseEntity<List<Lote>> findAll() {
        List<Lote> lista =  LConexion.findAll().getBody();
        for (Lote lote : lista) {
            lote.setPasosordenes(OService.GetPasoOrdenesLote(lote).getBody());
        }
        CustomResponseEntity<List<Lote>> response = new CustomResponseEntity<>();
        response.setBody(lista);
        return response;
    }

    @Override
    public CustomResponseEntity<Lote> Save(Lote lote) {
        CustomResponseEntity<Lote> response = new CustomResponseEntity<>();
        Lote Savedlote = LConexion.save(lote).getBody();
        System.out.println(Savedlote);
        for (PasoOrden orden : lote.getPasosordenes()) {
            System.out.println(orden);
            LConexion.savePasoLote(orden.getIdPasoOrden(),Savedlote.getIdlote());
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
        List<Lote> lista =  LConexion.findAllFECHA(fecha).getBody();
        for (Lote lote : lista) {
            List<Orden> Ordenes = OService.GetOrdenesLote(lote).getBody();
            lote.setOrdenes(Ordenes);
        }
        CustomResponseEntity<List<Lote>> response = new CustomResponseEntity<>();
        response.setBody(lista);
        return response;
    }
}
