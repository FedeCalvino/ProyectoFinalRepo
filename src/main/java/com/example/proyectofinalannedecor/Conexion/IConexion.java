/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.proyectofinalannedecor.Conexion;


import java.util.List;

/**
 *
 * @author calvi
 */
public interface IConexion<T> {
    T save(T t);
    T findById(Integer id);
    T Update(T t);
    T delete(Integer id);
    List<T> findAll();
}
