package com.example.proyectofinalannedecor.Clases;

import java.math.BigDecimal;


public class Cliente {
    Integer Id;
    BigDecimal Rut;
    String Nombre;
    BigDecimal NumeroTelefono;
    String direccion;
    String Mail;
    String Tipo;



    public Cliente(Integer Id, BigDecimal rut,String Nombre, BigDecimal NumeroTelefono, String direccion, String Tipo,String Mail) {
        this.Id = Id;
        this.Mail=Mail;
        this.Nombre = Nombre;
        this.direccion = direccion;
        Rut=rut;
        this.NumeroTelefono = NumeroTelefono;
        this.Tipo = Tipo;
    }

    public String   getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    @Override
    public String toString() {
        return Nombre;
    }

    public int compareTo(Cliente c) {
        int asciiValue1 = c.Nombre.charAt(0);
        int asciiValue2 = this.Nombre.charAt(0);
        if(asciiValue1>asciiValue2){
            return 1;
        }else if(asciiValue1==asciiValue2){
            return 0;
        }
        return -1;
    }

    public Integer getId() {
        return Id;
    }

    public BigDecimal getRut() {
        return Rut;
    }

    public String getNombre() {
        return Nombre;
    }

    public BigDecimal getNumeroTelefono() {
        return NumeroTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setRut(BigDecimal rut) {
        Rut = rut;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setNumeroTelefono(BigDecimal numeroTelefono) {
        NumeroTelefono = numeroTelefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
