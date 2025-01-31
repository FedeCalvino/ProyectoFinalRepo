package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulos.Articulo;
import com.example.proyectofinalannedecor.Clases.Articulos.Tradicional;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.*;
import com.example.proyectofinalannedecor.Conexion.OrdenConexion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService implements IService<Orden> {

    private static final OrdenConexion OrdenConexion= new OrdenConexion();

    private ArticuloService articuloService = new ArticuloService();


    public Orden CrearNuevaOrdenRoller(Articulo articulo) {

        Orden orden = new Orden(0,new ArrayList<>(),EstadosPasosOrden.TELA_CANO,articulo.getIdArticulo());

        orden.setArticulo(articulo);
        OrdenConexion.save(orden);

        PasoOrden paso1 = new PasoOrden(0,false,null,PasosArticulo.CORTE_TELA);
        PasoOrden paso2 =  new PasoOrden(0,false,null,PasosArticulo.CORTE_CANO);
        PasoOrden paso3 =  new PasoOrden(0,false,null,PasosArticulo.ARMADO);


        agregarPaso(orden,paso1);
        agregarPaso(orden,paso2);
        agregarPaso(orden,paso3);



        return orden;
    }

    public Orden CrearNuevaOrdenRiel(Articulo articulo) {

        Orden orden = new Orden(0,new ArrayList<>(),EstadosPasosOrden.CORTE_ARMADO,articulo.getIdArticulo());

        orden.setArticulo(articulo);
        OrdenConexion.save(orden);

        PasoOrden paso1 = new PasoOrden(0,false,null,PasosArticulo.CORTE_RIEL);
        PasoOrden paso2 =  new PasoOrden(0,false,null,PasosArticulo.ARMADO);


        agregarPaso(orden,paso1);
        agregarPaso(orden,paso2);

        return orden;
    }

    public CustomResponseEntity<List<Articulo>> findArticulosByIdOrden(Integer id) {
        return articuloService.findArticulosByIdOrden(id);
    }

    private void agregarPaso(Orden orden, PasoOrden nuevoPaso) {
        orden.getPasos().add(nuevoPaso);
        OrdenConexion.savePasoOrden(nuevoPaso,orden);
    }

    public CustomResponseEntity<List<Orden>> GetOrdenesLote(Lote l) {
        CustomResponseEntity<List<Orden>> respuesta = new CustomResponseEntity<>();
        List<Orden> ordenes = new ArrayList<>();
        List<PasoOrden> pasos = OrdenConexion.GetOrdenesLote(l).getBody();
        ArrayList<Integer> IdOrdenes = new ArrayList<>();

        if (pasos != null) {
            for (PasoOrden paso : pasos) {
                if (!IdOrdenes.contains(paso.getIdOrden())) {
                    List<PasoOrden> pasosAdd = new ArrayList<>();
                    System.out.println((paso.getIdOrden()));
                    Orden ord = OrdenConexion.findById(paso.getIdOrden()).getBody();

                    if (ord != null) {
                        for (PasoOrden paso2 : pasos) {
                            if (ord.getIdOrden() == paso2.getIdOrden()) {
                                pasosAdd.add(paso2);
                            }
                        }
                        ord.setPasos(pasosAdd);
                        ord.setArticulo(articuloService.findById(ord.getIdArticulo()).getBody());
                        ordenes.add(ord);
                        IdOrdenes.add(paso.getIdOrden());
                    }
                }
            }
        }

        respuesta.setBody(ordenes);
        return respuesta;
    }

    public CustomResponseEntity<List<PasoOrden>> GetPasoOrdenesLote(Lote l) {
        CustomResponseEntity<List<PasoOrden>> respuesta = new CustomResponseEntity<>();
        List<PasoOrden> pasos = OrdenConexion.GetOrdenesLote(l).getBody();

        respuesta.setBody(pasos);
        return respuesta;
    }


    @Override
    public CustomResponseEntity<List<Orden>> findAll() {
        CustomResponseEntity<List<Orden>> ordenes = OrdenConexion.findAll();
        for (Orden orden : ordenes.getBody()) {
            orden.setArticulo(articuloService.findById(orden.getIdArticulo()).getBody());
        }
        return ordenes;
    }

    public CustomResponseEntity<Orden> findAllPasos(Orden o) {
        CustomResponseEntity<Orden> retur = new CustomResponseEntity<Orden>();
        List<PasoOrden> pasos = OrdenConexion.selectPasosOrden(o.getIdOrden()).getBody();
        o.setPasos(pasos);
        retur.setBody(o);
        return retur;
    }

    public CustomResponseEntity<List<PasoOrden>> findAllPasosSinTerminar(Orden o) {
        CustomResponseEntity<List<PasoOrden>> retur = new CustomResponseEntity<>();
        List<PasoOrden> pasos = OrdenConexion.selectPasosOrdenSinTerminar(o.getIdOrden()).getBody();
        retur.setBody(pasos);
        return retur;
    }
    @Override
    public CustomResponseEntity<Orden> Save(Orden Clase) {
        return null;
    }

    @Override
    public CustomResponseEntity<Orden> update(Orden Clase) {
        return OrdenConexion.update(Clase);
    }

    @Override
    public CustomResponseEntity<Orden> delete(int id) {
        return null;
    }

    @Override
    public CustomResponseEntity<Orden> findById(int id) {
        return null;
    }

    public boolean avanzarPaso(int id) {
        return OrdenConexion.UpdatePaso(id);
    }

    public void deleteOrdenArticulo(Articulo art) {
        Orden o = OrdenConexion.finOrdenByArticulo(art.getIdArticulo());
        OrdenConexion.deletePasosOrden(o.getIdOrden());
        OrdenConexion.deleteOrdenArticuloId(art.getIdArticulo());
    }

    public Orden CrearNuevaOrdenTradicional(Tradicional tradicional) {
        Orden orden = new Orden(0,new ArrayList<>(),EstadosPasosOrden.CONFECCION,tradicional.getIdArticulo());

        orden.setArticulo(tradicional);
        OrdenConexion.save(orden);

        PasoOrden paso1 = new PasoOrden(0,false,null,PasosArticulo.CONFECCION);

        agregarPaso(orden,paso1);

        return orden;
    }
}
