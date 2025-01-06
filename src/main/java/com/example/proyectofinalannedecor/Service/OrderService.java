package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulo;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Instalacion;
import com.example.proyectofinalannedecor.Clases.Orden.*;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Tradicional;
import com.example.proyectofinalannedecor.Conexion.OrdenConexion;
import org.hibernate.annotations.Check;
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
        PasoOrden paso1 = new PasoOrden(false,null,PasosArticulo.CORTE_TELA);
        PasoOrden paso2 =  new PasoOrden(false,null,PasosArticulo.CORTE_CANO);
        PasoOrden paso3 =  new PasoOrden(false,null,PasosArticulo.ARMADO);
        PasoOrden paso4 =  new PasoOrden(false,null,PasosArticulo.INSTALACION);

        agregarPaso(orden,paso1);
        agregarPaso(orden,paso2);
        agregarPaso(orden,paso3);
        agregarPaso(orden,paso4);

        return orden;
    }

    public CustomResponseEntity<List<Articulo>> findArticulosByIdOrden(Integer id) {
        return articuloService.findArticulosByIdOrden(id);
    }


    public Orden AvanzarPasoRoller(PasoOrden paso,Orden orden){

        boolean CorteCano=false;
        boolean CorteTela=false;
        boolean Armado=false;
        boolean pasoInstalacion=false;

        for (PasoOrden pasoOrden : orden.getPasos()) {
            if(pasoOrden.getPaso().name().equals("CORTE_CANO") && pasoOrden.getTerminada()){
                CorteCano=true;
            }
            if(pasoOrden.getPaso().name().equals("CORTE_TELA") && pasoOrden.getTerminada()){
                CorteTela=true;
            }
            if(pasoOrden.getPaso().name().equals("ARMADO") && pasoOrden.getTerminada()){
                Armado=true;
            }
            if(pasoOrden.getPaso().name().equals("INSTALACION") && pasoOrden.getTerminada()){
                pasoInstalacion=true;
            }
        }

        if (paso.getPaso().name().equals("CORTE_CANO") || paso.getPaso().name().equals("CORTE_TELA")) {
            if(CorteCano && CorteTela && !Armado){
                PasoOrden pasoorden = new PasoOrden(false,null,PasosArticulo.ARMADO);
                orden.setEstado(EstadosPasosOrden.ARMANDO);
                OrdenConexion.update(orden);
                agregarPaso(orden,pasoorden);
            }
        }

        if (paso.getPaso().name().equals("ARMADO") && !pasoInstalacion){
            if (paso.getTerminada()) {
                PasoOrden pasoorden = new PasoOrden(false,null,PasosArticulo.INSTALACION);
                orden.setEstado(EstadosPasosOrden.INSTALANDO);
                OrdenConexion.update(orden);
                agregarPaso(orden, pasoorden);
            }
        }

        return orden;
    }

    public Orden AvanzarPasoTradicional(PasoOrden paso,Orden orden){

        boolean Confeccion=false;
        boolean CorteRiel=false;
        boolean ArmadoRiel=false;
        boolean pasoInstalacion=false;

        for (PasoOrden pasoOrden : orden.getPasos()) {
            if(pasoOrden.getPaso().name().equals("CONFECCION") && pasoOrden.getTerminada()){
                Confeccion=true;
            }
            if(pasoOrden.getPaso().name().equals("CORTE_RIEL") && pasoOrden.getTerminada()){
                CorteRiel=true;
            }
            if(pasoOrden.getPaso().name().equals("ARMADO_RIEL") && pasoOrden.getTerminada()){
                ArmadoRiel=true;
            }
            if(pasoOrden.getPaso().name().equals("INSTALACION") && pasoOrden.getTerminada()){
                pasoInstalacion=true;
            }
        }

        if (paso.getPaso().name().equals("CORTE_RIEL")) {
                PasoOrden pasoorden = new PasoOrden(false,null,PasosArticulo.ARMADO_RIEL);
                agregarPaso(orden,pasoorden);
        }

        if (paso.getPaso().name().equals("ARMADO") && !pasoInstalacion){
            if (paso.getTerminada()) {
                PasoOrden pasoorden = new PasoOrden(false,null,PasosArticulo.INSTALACION);
                agregarPaso(orden, pasoorden);
            }
        }

        return orden;
    }

    public Orden avanzarPaso(String NombrePaso ,Orden orden){

        if (NombrePaso == null) {
            return null;
        }

        PasoOrden paso=null;
        List<PasoOrden> pasos = OrdenConexion.selectPasosOrden(orden.getIdOrden()).getBody();
        orden.setPasos(pasos);
        for(PasoOrden pasofor : orden.getPasos()) {
            if(pasofor.getPaso().name().equals(NombrePaso)) {
                paso=pasofor;
            }
        }

        if (!paso.getTerminada()) {

            paso.setTerminada(true);
            paso.setFinalizado(new java.sql.Date(System.currentTimeMillis()));
            OrdenConexion.updatePasoOrden(paso);
            for(PasoOrden pasofor : orden.getPasos()) {
                if(pasofor.getPaso().name().equals(NombrePaso)) {
                    if(!pasofor.getTerminada()) {
                        pasofor.setTerminada(true);
                        pasofor.setFinalizado(new java.sql.Date(System.currentTimeMillis()));
                    }
                }
            }
            if(orden.getArticulo() instanceof Roller) {
                AvanzarPasoRoller(paso, orden);
            }
            if(orden.getArticulo() instanceof Tradicional) {
                AvanzarPasoTradicional(paso, orden);
            }

        } else {
            System.out.println("El paso '" + paso.toString() + "' ya está completado.");
        }
        return orden;
    }

    private void agregarPaso(Orden orden, PasoOrden nuevoPaso) {
        orden.getPasos().add(nuevoPaso);
        OrdenConexion.savePasoOrden(nuevoPaso,orden);
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
}
