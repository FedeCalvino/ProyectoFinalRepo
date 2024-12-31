package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.Articulo;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.Orden.EstadosPasosOrden;
import com.example.proyectofinalannedecor.Clases.Orden.PasosArticulo;
import com.example.proyectofinalannedecor.Clases.Orden.Orden;
import com.example.proyectofinalannedecor.Clases.Orden.PasoOrden;
import com.example.proyectofinalannedecor.Clases.TipoCortina.Roller;
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
        Orden orden = new Orden(0,new ArrayList<>(),articulo,null);
        OrdenConexion.save(orden);
        agregarPaso(orden,new PasoOrden(false,null,PasosArticulo.CORTE_TELA));
        agregarPaso(orden,new PasoOrden(false,null,PasosArticulo.CORTE_CANO));
        return orden;
    }

    public CustomResponseEntity<List<Articulo>> findArticulosByIdOrden(Integer id) {
        return articuloService.findArticulosByIdOrden(id);
    }

    public boolean CheckPassCorteTela(int ordenId){
        return false;
    }

    public Orden avanzarPaso(String NombrePaso ,Orden orden){

        if (NombrePaso == null) {
            return null;
        }

        PasoOrden paso=null;
        PasoOrden pasoArmado=null;
        PasoOrden pasoInst=null;

        for(PasoOrden pasofor : orden.getPasos()) {
            if(pasofor.getPaso().name().equals(NombrePaso)) {
                paso=pasofor;
            }
        }
        if (!paso.getTerminada()) {
            paso.setTerminada(true);
            paso.setFinalizado(new java.sql.Date(System.currentTimeMillis()));
            OrdenConexion.updatePasoOrden(paso);
        } else {
            System.out.println("El paso '" + paso.toString() + "' ya está completado.");
        }
            if (paso.getPaso().name().equals("CORTE_CANO") || paso.getPaso().name().equals("CORTE_TELA")) {

                boolean CorteCano=false;
                boolean CanoTela=false;

                for (PasoOrden pasoOrden : orden.getPasos()) {
                    if(pasoOrden.getPaso().name().equals("CORTE_CANO") && pasoOrden.getTerminada()){
                        CorteCano=true;
                    }
                    if(pasoOrden.getPaso().name().equals("CORTE_TELA") && pasoOrden.getTerminada()){
                        CanoTela=true;
                    }
                }

                if(CorteCano && CanoTela && pasoArmado==null){
                    PasoOrden pasoorden = new PasoOrden(false,null,PasosArticulo.ARMADO);
                    agregarPaso(orden,pasoorden);
                }

            }

            if (paso.getPaso().name().equals("ARMADO") && pasoInst==null){
                if (paso.getTerminada()) {
                    PasoOrden pasoorden = new PasoOrden(false,null,PasosArticulo.INSTALACION);
                    agregarPaso(orden, pasoorden);
                }
            }
        return orden;
    }

    private void agregarPaso(Orden orden, PasoOrden nuevoPaso) {
        orden.getPasos().add(nuevoPaso);
        OrdenConexion.savePasoOrden(nuevoPaso,orden);
    }

    @Override
    public CustomResponseEntity<List<Orden>> findAll() {
        return OrdenConexion.findAll();
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
