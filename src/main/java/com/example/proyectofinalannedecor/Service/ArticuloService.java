package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Riel;
import com.example.proyectofinalannedecor.Clases.Articulos.Roller;
import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Conexion.ArticuloConexion;
import com.example.proyectofinalannedecor.Conexion.OrdenConexion;

import java.util.ArrayList;
import java.util.List;

public class ArticuloService implements IService<Articulo>{
    private static final ArticuloConexion articuloConexion= new ArticuloConexion();
    private static final RollerService rollerService= new RollerService();
    private static final RielService rielService= new RielService();
    private static final CortinaService cortinaService= new CortinaService();
    private static final OrderService ordenService= new OrderService();
    private static final TradicionalService tradicionalService= new TradicionalService();

    @Override
    public CustomResponseEntity<List<Articulo>> findAll() {
        return null;
    }

    @Override
    public CustomResponseEntity<Articulo> Save(Articulo articulo) {
        return articuloConexion.save(articulo);
    }

    public CustomResponseEntity<List<Articulo>> findArticulosByIdOrden(Integer id) {
        return articuloConexion.findArticulosByIdOrden(id);
    }

    @Override
    public CustomResponseEntity<Articulo> update(Articulo articulo) {
        return articuloConexion.update(articulo);
    }

    @Override
    public CustomResponseEntity<Articulo> delete(int id) {
        return articuloConexion.delete(id);
    }

    public CustomResponseEntity<Articulo> deleteArticulo(Articulo art) {
        System.out.println("ID ARTICULO :"+ art.getIdArticulo());
        ordenService.deleteOrdenArticulo(art);
        if(art.getNombre().equals("Roller")){
            Roller r = rollerService.findRollerArticulo(art).getBody();
            rollerService.delete(r.getIdRoller());
            cortinaService.deleteFromArticulo(art.getIdArticulo());
        }
        if(art.getNombre().equals("Riel")){
            Riel r = rielService.findRielArticulo(art).getBody();
            rielService.delete(r.getIdRiel());
        }
        if(art.getNombre().equals("Tradicional")){
            Tradicional t = tradicionalService.findTradicionalArticulo(art).getBody();
            tradicionalService.delete(t.getTradicionalId());
            cortinaService.deleteFromArticulo(art.getIdArticulo());
        }
        CustomResponseEntity<Articulo> response = articuloConexion.delete(art.getIdArticulo());
        return response;
    }


    @Override
    public CustomResponseEntity<Articulo> findById(int id) {

        CustomResponseEntity<Articulo> response = articuloConexion.findById(id);
        System.out.println(response.getBody());

        if (response.getBody().getNombre().equals("Roller")) {
            Articulo articuloret= rollerService.findRollerArticulo(response.getBody()).getBody();
            articuloret.setIdArticulo(response.getBody().getIdArticulo());
            articuloret.setNumeroArticulo(response.getBody().getNumeroArticulo());
            response.setBody(articuloret);
            response.setMessage("OK");
        }
        if (response.getBody().getNombre().equals("Riel")) {
            Articulo articuloret = rielService.findRielArticulo(response.getBody()).getBody();
            articuloret.setIdArticulo(response.getBody().getIdArticulo());
            articuloret.setNumeroArticulo(response.getBody().getNumeroArticulo());
            response.setBody(articuloret);
            response.setMessage("OK");
        }

        return response;
    }

    public CustomResponseEntity<List<Articulo>> findArticulos(int Venta_Id) {
        CustomResponseEntity<List<Articulo>> response  = new CustomResponseEntity<>();
        List<Articulo> articulos  = articuloConexion.findArticulosVenta(Venta_Id).getBody();
        List<Articulo> articulosVenta  = new ArrayList<>();
        if(articulos!=null){
            for (Articulo articulo : articulos) {
                System.out.println(articulo.getNombre());
                if (articulo.getNombre().equals("Roller")) {
                    Articulo articuloAdd = rollerService.findRollerArticulo(articulo).getBody();
                    if (articuloAdd != null) {
                        articuloAdd.setIdArticulo(articulo.getIdArticulo());
                        articuloAdd.setNumeroArticulo(articulo.getNumeroArticulo());
                        articuloAdd.setDetalleInstalacion(articulo.getDetalleInstalacion());
                        articulosVenta.add(articuloAdd);
                    }
                }
                if (articulo.getNombre().equals("Riel")) {

                    Articulo articuloAdd = rielService.findRielArticulo(articulo).getBody();
                    if (articuloAdd != null) {
                        articuloAdd.setIdArticulo(articulo.getIdArticulo());
                        articuloAdd.setNumeroArticulo(articulo.getNumeroArticulo());
                        articuloAdd.setDetalleInstalacion(articulo.getDetalleInstalacion());
                        articulosVenta.add(articuloAdd);
                    }
                }
                if (articulo.getNombre().equals("Tradicional")) {
                    System.out.println("entro en tradicional");
                    System.out.println("articuloid"+articulo.getIdArticulo());
                    Articulo articuloAdd = tradicionalService.findTradicionalArticulo(articulo).getBody();
                    if (articuloAdd != null) {
                        System.out.println("articuloadd");
                        articuloAdd.setIdArticulo(articulo.getIdArticulo());
                        articuloAdd.setNumeroArticulo(articulo.getNumeroArticulo());
                        articuloAdd.setDetalleInstalacion(articulo.getDetalleInstalacion());
                        articulosVenta.add(articuloAdd);
                    }
                }
            }
        }

        response.setBody(articulosVenta);
        return response;
    }

    public CustomResponseEntity<List<Articulo>> findArticulosByIdVenta(int id) {
        return articuloConexion.findArticulosVenta(id);
    }
}
