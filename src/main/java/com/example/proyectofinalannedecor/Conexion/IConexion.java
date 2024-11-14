/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.proyectofinalannedecor.Conexion;


import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;

import java.util.List;

/**
 *
 * @author calvi
 */
public interface IConexion<T> {
    CustomResponseEntity<T> save(T t);
    CustomResponseEntity<T> findById(Integer id);
    CustomResponseEntity<T> update(T t);
    CustomResponseEntity<T> delete(Integer id);
    CustomResponseEntity<List<T>> findAll();
}
