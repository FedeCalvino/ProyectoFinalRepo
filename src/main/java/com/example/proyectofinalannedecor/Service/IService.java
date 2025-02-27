package com.example.proyectofinalannedecor.Service;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;

import java.util.List;

public interface IService<T> {

    CustomResponseEntity<List<T>> findAll();


    CustomResponseEntity<T> Save(T Clase);


    CustomResponseEntity<T> update(T Clase);

    CustomResponseEntity<T> delete(int id);


    CustomResponseEntity<T> findById(int id);
}
