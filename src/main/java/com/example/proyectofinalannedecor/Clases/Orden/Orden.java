package com.example.proyectofinalannedecor.Clases.Orden;

import com.example.proyectofinalannedecor.Clases.Articulo;

import java.util.Date;
import java.util.List;


public class Orden {
    int IdOrden;
    Articulo Articulo;
    String Estado;
    Date Finalizado;
    List<PasoOrden> Pasos;



}
