package com.example.proyectofinalannedecor.Controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

    public interface IController<T> {


        CustomResponseEntity<List<T>> findAll();


        CustomResponseEntity<T> Save(T venta);


        CustomResponseEntity<T> update(T venta);

        CustomResponseEntity<T> delete(int id);


        CustomResponseEntity<T> findById(int id);

    }

