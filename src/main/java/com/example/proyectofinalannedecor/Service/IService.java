package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Controller.CustomResponseEntity;

import java.util.List;

public interface IService<T> {

    CustomResponseEntity<List<T>> findAll();


    CustomResponseEntity<T> Save(T venta);


    CustomResponseEntity<T> update(T venta);

    CustomResponseEntity<T> delete(int id);


    CustomResponseEntity<T> findById(int id);
}
