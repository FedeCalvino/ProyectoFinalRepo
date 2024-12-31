package com.example.proyectofinalannedecor.Clases.Orden;

import java.util.*;

public class Pasos_Estados {
    private static final Map<String, List<PasosArticulo>> pasosPorArticuloRoller = new HashMap<>();
    private static final Map<String, List<EstadosPasosOrden>> estadosOrdenRoller = new HashMap<>();


    static {
        pasosPorArticuloRoller.put("Roller", Arrays.asList(
                PasosArticulo.CORTE_TELA,
                PasosArticulo.CORTE_CANO,
                PasosArticulo.ARMADO,
                PasosArticulo.INSTALACION
        ));
        estadosOrdenRoller.put("Orden", Arrays.asList(
                EstadosPasosOrden.ARMANDO,
                EstadosPasosOrden.TELA_CANO,
                EstadosPasosOrden.INSTALANDO
        ));
    }

    public static List<PasosArticulo> getPasosPorArticulo(String tipoArticulo) {
        return pasosPorArticuloRoller.getOrDefault(tipoArticulo, Collections.emptyList());
    }
}
