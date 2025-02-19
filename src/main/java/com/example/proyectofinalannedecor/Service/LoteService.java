package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.Lote;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Clases.Orden.PasoOrden;
import com.example.proyectofinalannedecor.Clases.Venta;
import com.example.proyectofinalannedecor.Conexion.LoteConexion;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoteService implements IService<Lote> {

    private static final LoteConexion LConexion= new LoteConexion();
    private static final OrderService OService= new OrderService();
    private static final SimpMessagingTemplate messagingTemplate = new SimpMessagingTemplate(new ExecutorSubscribableChannel());
    private static final VentaService VService = new VentaService(messagingTemplate);

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
        for (PasoOrden orden : lote.getPasosordenes()) {
            LConexion.savePasoLote(orden.getIdPasoOrden(),Savedlote.getIdlote());
        }
        // Enviar el lote como mensaje
        VService.MandarMensaje("Nuevo lote guardado: " + Savedlote.toString());
        response.setBody(Savedlote);
        response.setStatus(HttpStatus.OK);
        return response;
    }

    public String mensajee() {
        return VService.MandarMensaje("Nuevo Lote");
    }

    @Override
    public CustomResponseEntity<Lote> update(Lote Clase) {
        return LConexion.update(Clase);
    }

    @Override
    public CustomResponseEntity<Lote> delete(int id) {
        LConexion.DeleteLotePaaso(id);
        return LConexion.delete(id);
    }

    @Override
    public CustomResponseEntity<Lote> findById(int id) {
        return null;
    }

    public String MandarMensaje(String mensaje) {
        System.out.println(mensaje);
        this.MandarNuevaOrden("Nueva orden disponible");
        return "Mensaje enviado";
    }

    public void MandarNuevaOrden(String orderDetails) {
        messagingTemplate.convertAndSend("/topic/orders", orderDetails);
    }
    public void DeletePasoLote(int IdPasoOrden) {
        LConexion.DeletePasoLote(IdPasoOrden);
    }

    public CustomResponseEntity<List<Lote>> findAllFecha(String fecha) {
        List<Lote> lista =  LConexion.findAllFECHA(fecha).getBody();
        for (Lote lote : lista) {

            List<Orden> Ordenes = OService.GetOrdenesLote(lote).getBody();
            ArrayList<Integer> idOrdenes = new ArrayList<>();
            ArrayList<Venta> ventas = new ArrayList<>();

            for(Orden orden : Ordenes) {
                if(!idOrdenes.contains(orden.getIdVenta())){
                    idOrdenes.add(orden.getIdVenta());
                    Venta v = VService.findByIdSoloVenta(orden.getIdVenta()).getBody();
                    v.addOrden(orden);
                    ventas.add(v);
                }else{
                    for(Venta v : ventas) {
                        if(v.getId()==orden.getIdVenta()){
                            v.addOrden(orden);
                        }
                    }
                }
            }
            lote.setVentas(ventas);
        }
        CustomResponseEntity<List<Lote>> response = new CustomResponseEntity<>();
        response.setBody(lista);
        response.setStatus(HttpStatus.OK);
        return response;
    }
}
