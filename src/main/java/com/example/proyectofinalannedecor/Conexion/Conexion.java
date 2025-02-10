package com.example.proyectofinalannedecor.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {


	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static final String connectionUrl = "jdbc:sqlserver://200.40.89.254:1433;databaseName=SistemaTestingAnneDecor;user=SA;password=Federico.1809;";

    public static Connection GetConexion() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error de SQL al intentar conectar: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }


}
