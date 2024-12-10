package com.example.proyectofinalannedecor.Controller;

import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

    public interface IController<T> {

        @GetMapping
        CustomResponseEntity<List<T>> findAll();


        CustomResponseEntity<T> Save(@RequestBody T clase);


        CustomResponseEntity<T> update(@RequestBody T clase);

        CustomResponseEntity<T> delete(int id);


        CustomResponseEntity<T> findById(int id);

    }

