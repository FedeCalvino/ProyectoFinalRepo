package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

    public interface IController<T> {

        @GetMapping
        CustomResponseEntity<List<T>> findAll();


        CustomResponseEntity<T> Save(T venta);


        CustomResponseEntity<T> update(T venta);

        CustomResponseEntity<T> delete(int id);


        CustomResponseEntity<T> findById(int id);

    }

