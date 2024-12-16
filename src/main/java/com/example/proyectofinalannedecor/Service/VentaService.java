package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.*;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Conexion.CortinaConexion;
import com.example.proyectofinalannedecor.Conexion.VentasConexion;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VentaService implements IService<Venta>{

    private static final VentasConexion VentasConexion= new VentasConexion();
    private static final ClienteService ClienteService = new ClienteService();
    private static final CortinaService CortinaService = new CortinaService();
    private static final ArticuloService ArticuloService = new ArticuloService();
    private static final RollerService RollerService = new RollerService();
    public VentaService() {

    }

    @Override
    public CustomResponseEntity<List<Venta>> findAll() {

        CustomResponseEntity<List<Venta>> ventas = VentasConexion.findAll();

        for(Venta v : ventas.getBody()){
            System.out.println(v.getId());
            //Roller
            List<Articulo> articulos = ArticuloService.findArticulos(v.getId()).getBody();
            v.setArticulos(articulos);
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
                    CustomResponseEntity<Articulo> responseArticulo = ArticuloService.Save(articulo);
                    if(responseArticulo.getStatus()!= HttpStatus.CREATED) {
                        responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                        responseVenta.setMessage(responseArticulo.getMessage());
                        return responseVenta;
                    }
                    if(articulo instanceof Roller){
                        boolean ventaArticulo = VentasConexion.SaveArticuloVenta(responseArticulo.getBody().getIdArticulo(),responseVenta.getBody().getId());

                        if(!ventaArticulo) {
                            responseVenta.setStatus(HttpStatus.BAD_REQUEST);
                            responseVenta.setMessage(responseArticulo.getMessage());
                            return responseVenta;
                        }

                            Roller roller = (Roller) articulo;
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

                    }
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
        return responseVenta;
    }

    @Override
    public CustomResponseEntity<Venta> update(Venta venta) {
        return VentasConexion.update(venta);
    }

    @Override
    public CustomResponseEntity<Venta> delete(int id) {
        return VentasConexion.delete(id);
    }

    @Override
    public CustomResponseEntity<Venta> findById(int id) {
        return VentasConexion.findById(id);
    }

}

