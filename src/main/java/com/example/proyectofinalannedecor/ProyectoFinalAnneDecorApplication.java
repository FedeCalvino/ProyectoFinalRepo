package com.example.proyectofinalannedecor;

import com.example.proyectofinalannedecor.Conexion.Conexion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.Connection;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ProyectoFinalAnneDecorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoFinalAnneDecorApplication.class, args);

    }

}
