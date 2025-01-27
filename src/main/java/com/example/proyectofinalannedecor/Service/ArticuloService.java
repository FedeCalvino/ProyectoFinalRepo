package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Conexion.ArticuloConexion;

import java.util.ArrayList;
import java.util.List;

public class ArticuloService implements IService<Articulo>{
    private static final ArticuloConexion articuloConexion= new ArticuloConexion();
    private static final RollerService rollerService= new RollerService();
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
        return null;
    }

    @Override
    public CustomResponseEntity<Articulo> delete(int id) {
        return null;
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
            Articulo articuloret = rollerService.findRielArticulo(response.getBody()).getBody();
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
                    articuloAdd.setIdArticulo(articulo.getIdArticulo());
                    if (articuloAdd != null) {
                        articulosVenta.add(articuloAdd);
                    }
                }
                if (articulo.getNombre().equals("Riel")) {

                    Articulo articuloAdd = rollerService.findRielArticulo(articulo).getBody();
                    articuloAdd.setIdArticulo(articulo.getIdArticulo());
                    if (articuloAdd != null) {
                        articulosVenta.add(articuloAdd);
                    }
                }
            }
        }

        response.setBody(articulosVenta);
        return response;
    }
}
