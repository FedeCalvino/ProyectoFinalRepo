package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.*;
import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Conexion.VentasConexion;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VentaService implements IService<Venta>{

    private static final VentasConexion VentasConexion= new VentasConexion();
    private static final ClienteService ClienteService = new ClienteService();
    private static final CortinaService CortinaService = new CortinaService();
    private static final ArticuloService ArticuloService = new ArticuloService();
    private static final RollerService RollerService = new RollerService();
    private static final OrderService orderService = new OrderService();
    private static final LoteService LoteService = new LoteService();
    private static final RielService RielService = new RielService();
    private static final TradicionalService TradiService = new TradicionalService();
    private static final InstalacionService instalacionService = new InstalacionService();
    private final SimpMessagingTemplate messagingTemplate;

    public VentaService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public CustomResponseEntity<List<Venta>> findAll() {

        CustomResponseEntity<List<Venta>> ventas = VentasConexion.findAll();
        for(Venta v : ventas.getBody()){
            v.setCliente(ClienteService.findById(v.getCliente().getId()).getBody());
        }
        return ventas;
    }

    @Override
    public CustomResponseEntity<Venta> Save(Venta venta) {

        CustomResponseEntity<Venta> responseVenta = new CustomResponseEntity<>();

        CustomResponseEntity<Cliente> responseCliente = ClienteService.Save(venta.getCliente());

        if(responseCliente.getStatus() == HttpStatus.CREATED) {

            responseVenta = VentasConexion.save(venta);

            if (responseVenta.getStatus() == HttpStatus.OK) {
                Cliente c = responseCliente.getBody();
                venta.setCliente(c);

                for (Articulo articulo : venta.getListaArticulos()) {
                    articulo.setIdVenta(responseVenta.getBody().getId());
                    CustomResponseEntity<Articulo> responseArticulo = ArticuloService.Save(articulo);

                    if(responseArticulo.getStatus()!= HttpStatus.CREATED) {
                        responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                        responseVenta.setMessage(responseArticulo.getMessage());
                        return responseVenta;
                    }


                    if(articulo instanceof Roller){

                            Roller roller = (Roller) articulo;
                            roller.setIdArticulo(responseArticulo.getBody().getIdArticulo());
                            CustomResponseEntity<Cortina> responseCortina = CortinaService.Save(roller);

                            if (responseCortina.getStatus() != HttpStatus.OK) {
                                responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                                responseVenta.setMessage(responseCortina.getMessage());
                                return responseVenta;
                            }

                            CustomResponseEntity<Roller> responseRoller = RollerService.Save(roller);
                            if (responseRoller.getStatus() != HttpStatus.OK) {
                                responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                                responseVenta.setMessage(responseRoller.getMessage());
                                return responseVenta;
                            }

                            Orden orden = orderService.CrearNuevaOrdenRoller(responseRoller.getBody());
                    }
                    if(articulo instanceof Riel){

                        Riel riel = (Riel) articulo;
                        CustomResponseEntity<Bastones> responseBaston = RielService.SaveBaston(riel.getBastones());
                        riel.setBastones(responseBaston.getBody());

                        CustomResponseEntity<Soporte> responseSoporte = RielService.SaveSoporte(riel.getSoportes());
                        riel.setSoportes(responseSoporte.getBody());

                        riel.setIdArticulo(responseArticulo.getBody().getIdArticulo());

                        CustomResponseEntity<Riel> responseRiel = RielService.Save(riel);



                        if (responseRiel.getStatus() != HttpStatus.OK) {
                            responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                            responseVenta.setMessage(responseRiel.getMessage());
                            return responseVenta;
                        }

                        Orden orden = orderService.CrearNuevaOrdenRiel(responseRiel.getBody());
                    }
                    if(articulo instanceof Tradicional){
                        Tradicional tradicional = (Tradicional) articulo;
                        tradicional.setIdArticulo(responseArticulo.getBody().getIdArticulo());

                        CustomResponseEntity<Cortina> responseCortina = CortinaService.Save(tradicional);
                        tradicional.setId(responseCortina.getBody().getId());
                        CustomResponseEntity<Tradicional> responseTradicional = TradiService.Save(tradicional);

                        if (responseCortina.getStatus() != HttpStatus.OK) {
                            responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                            responseVenta.setMessage(responseCortina.getMessage());
                            return responseVenta;
                        }

                        if (responseTradicional.getStatus() != HttpStatus.OK) {
                            responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                            responseVenta.setMessage(responseTradicional.getMessage());
                            return responseVenta;
                        }

                        Orden orden = orderService.CrearNuevaOrdenTradicional(responseTradicional.getBody());

                    }
                }

                if(responseVenta.getBody().fechaInstalacion!=null){
                    String start = responseVenta.getBody().fechaInstalacion +" 00:00:00.000";
                    String end = responseVenta.getBody().fechaInstalacion +" 00:00:00.000";
                    Instalacion instalacion = new Instalacion(
                            responseVenta.getBody().getId(),
                            start,
                            "",
                            end,
                            responseVenta.getBody().getCliente().getNombre()
                    );
                    instalacionService.Save(instalacion);
                }

                responseVenta.setStatus(HttpStatus.OK);
                responseVenta.setBody(venta);

            }else{
                responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                responseVenta.setMessage(responseVenta.getMessage());
            }
        }else{
            responseVenta.setStatus(HttpStatus.BAD_REQUEST);
            responseVenta.setMessage(responseCliente.getMessage());
        }
        MandarMensaje("Recarga");
        return responseVenta;
    }

    public String MandarMensaje(String mensaje) {
        System.out.println("mensaje");
        this.MandarNuevaOrden(mensaje);
        return "Mensaje enviado";
    }

    public void MandarNuevaOrden(String orderDetails) {
        messagingTemplate.convertAndSend("/topic/orders", orderDetails);
    }

    @Override
    public CustomResponseEntity<Venta> update(Venta venta) {
        return VentasConexion.update(venta);
    }

    @Override
    public CustomResponseEntity<Venta> delete(int id) {
        System.out.println("ventaid: "+id);
        List<Articulo> articulos = ArticuloService.findArticulos(id).getBody();

        for (Articulo articulo : articulos) {
            ArticuloService.deleteArticulo(articulo);
        }
        Instalacion inst = instalacionService.findByIdVenta(id).getBody();
        if(inst!=null){
            instalacionService.delete(inst.getId());
        }
        return VentasConexion.delete(id);
    }

    @Override
    public CustomResponseEntity<Venta> findById(int id) {
        CustomResponseEntity<Venta> responseVenta = new CustomResponseEntity<>();
        Venta v = VentasConexion.findById(id).getBody();
        v.setCliente(ClienteService.findById(v.getCliente().getId()).getBody());
        List<Articulo> articulos = ArticuloService.findArticulos(id).getBody();
        v.setArticulos(articulos);
        responseVenta.setBody(v);
        responseVenta.setMessage("Ok");
        return responseVenta;
    }

    public CustomResponseEntity<Venta> findByIdSoloVenta(int id) {
        System.out.println(id);
        CustomResponseEntity<Venta> responseVenta = new CustomResponseEntity<>();
        Venta v = VentasConexion.findById(id).getBody();
        v.setCliente(ClienteService.findById(v.getCliente().getId()).getBody());
        responseVenta.setBody(v);
        responseVenta.setMessage("Ok");
        return responseVenta;
    }

    public CustomResponseEntity<List<Venta>> findAllWordenes() {
        CustomResponseEntity<List<Venta>> ventas = VentasConexion.findAllWorden();
        return ventas;
    }

    public CustomResponseEntity<String> updateVentaFO(String instalacion, String obra,int IdVen) {
        if(instalacion.equals("null")){
        }else{
            instalacionService.UpdateFecha(instalacion,IdVen);
        }
        return VentasConexion.updateVentaFO(instalacion,obra,IdVen);
    }
}

