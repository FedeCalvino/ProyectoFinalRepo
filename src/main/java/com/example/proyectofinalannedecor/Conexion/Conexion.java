package com.example.proyectofinalannedecor.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static final String connectionUrl = "jdbc:sqlserver://anndedecorserver.database.windows.net;user=AnneDecor;password=Perrotati1;databaseName=AnneDecorDb;trustServerCertificate=true;encrypt=true";

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
