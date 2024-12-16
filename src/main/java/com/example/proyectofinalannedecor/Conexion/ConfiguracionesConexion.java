package com.example.proyectofinalannedecor.Conexion;

import com.example.proyectofinalannedecor.Clases.ConfiguracionCortinas.*;
import com.example.proyectofinalannedecor.Clases.CustomResponseEntity;
import com.example.proyectofinalannedecor.Clases.TipoCliente;
import org.springframework.http.HttpStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ConfiguracionesConexion {

    private static final String SQL_INSERT_LADO_CADENA = "INSERT INTO LADO_CADENA (NOMBRE) VALUES (?)";
    private static final String SQL_SELECT_LADO_CADENAS = "SELECT * FROM LADO_CADENA";
    private static final String SQL_SELECT_LADO_CADENA_BY_ID = "SELECT * FROM LADO_CADENA WHERE ID_LADO = ?";

    private static final String SQL_INSERT_POSICION = "INSERT INTO POSICION_ROLLER (posicion) VALUES (?)";
    private static final String SQL_SELECT_POSICIONES = "SELECT * FROM POSICION_ROLLER";
    private static final String SQL_SELECT_POSICION_BY_ID = "SELECT * FROM POSICION_ROLLER WHERE idPosicion = ?";

    private static final String SQL_INSERT_CANO = "INSERT INTO CANO (CANO) VALUES (?)";
    private static final String SQL_SELECT_CANOS = "SELECT * FROM CANO";
    private static final String SQL_SELECT_CANO_BY_ID = "SELECT * FROM CANO WHERE ID_CANO = ?";

    private static final String SQL_INSERT_MOTOR = "INSERT INTO TIPO_MOTOR (TIPO,NOMBRE) VALUES (?,?|)";
    private static final String SQL_SELECT_MOTORES = "SELECT * FROM TIPO_MOTOR";
    private static final String SQL_SELECT_MOTOR_BY_ID = "SELECT * FROM TIPO_MOTOR WHERE ID_MOTOR = ?";

    private static final String SQL_INSERT_TIPO_CADENA = "INSERT INTO TIPO_CADENA (NOPMBRE) VALUES (?)";
    private static final String SQL_SELECT_TIPO_CADENA = "SELECT * FROM TIPO_CADENA";
    private static final String SQL_SELECT_TIPO_CADENA_ID = "SELECT * FROM TIPO_CADENA WHERE ID_TIPO_CADENA = ?";

    private static final String SQL_SELECT_TIPO_CLIENTES = "SELECT * FROM TIPOS_CLIENTE";

    java.sql.Connection conexion = null;
    public CustomResponseEntity<ConfiguracionRoller> findAllConfRollers(){
        this.conexion = (java.sql.Connection) Conexion.GetConexion();

        ConfiguracionRoller configuracionRoller=new ConfiguracionRoller(
                this.GetPosiciones(),
                this.GetCanos(),
                this.GetMotores(),
                this.GetLadosCadenas(),
                this.GetTipoCadenas()
        );
        CustomResponseEntity<ConfiguracionRoller> response = new CustomResponseEntity<ConfiguracionRoller>();
        response.setStatus(HttpStatus.OK);
        response.setBody(configuracionRoller);
        return response;
    }

    public CustomResponseEntity<List<TipoCliente>> GetTiposClientes() {
        CustomResponseEntity<List<TipoCliente>> response = new CustomResponseEntity<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        List<TipoCliente> tipoClientes = new ArrayList<>();
        try {
            PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_TIPO_CLIENTES);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tipo = rs.getString(1);
                int tipoClienteId = rs.getInt(2);
                TipoCliente tipoClienteObj = new TipoCliente(tipoClienteId);
                tipoClienteObj.setTipo(tipo);
                tipoClientes.add(tipoClienteObj);

            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las posiciones: " + e.getMessage());
        }
        response.setStatus(HttpStatus.OK);
        response.setBody(tipoClientes);
        return response;
    }

    public CustomResponseEntity<Posicion> SavePosicion(Posicion posicion) {
        CustomResponseEntity<Posicion> response = new CustomResponseEntity<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try{
            PreparedStatement stmt = conexion.prepareStatement(SQL_INSERT_POSICION, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, posicion.getPosicion());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        posicion.setPosicionId(generatedId);// Actualizar el ID generado
                    }
                }
                response.setBody(posicion);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo insertar la posición");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar la posición: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Posicion GetPosicionPorId(int id) {
        Posicion posicion = null;
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_POSICION_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String posicionstr = rs.getString(1);
                    int posicionId = rs.getInt(2);
                    posicion = new Posicion(posicionId);
                    posicion.setPosicion(posicionstr);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el lado de cadena por ID: " + e.getMessage());
        }
        return posicion;
    }

    public List<Posicion> GetPosiciones() {
        List<Posicion> posiciones = new ArrayList<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try {
            PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_POSICIONES);

             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String posicion = rs.getString(1);
                int posicionId = rs.getInt(2);
                Posicion posicionObj = new Posicion(posicionId);
                posicionObj.setPosicion(posicion);
                posiciones.add(posicionObj);

            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las posiciones: " + e.getMessage());
        }
        return posiciones;
    }

    public CustomResponseEntity<Cano> SaveCano(Cano cano) {
        CustomResponseEntity<Cano> response = new CustomResponseEntity<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_INSERT_CANO, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cano.getTipo());
            stmt.setInt(2, cano.getId());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        cano.setId(generatedId); // Actualizar el ID generado
                    }
                }
                response.setBody(cano);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo insertar el caño");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el caño: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Cano GetCanoPorId(int id) {
        Cano Cano = null;
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_CANO_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int tipo = rs.getInt(1);
                    int canoId = rs.getInt(2);
                    Cano = new Cano(canoId);
                    Cano.setTipo(tipo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el lado de cadena por ID: " + e.getMessage());
        }
        return Cano;
    }

    public List<Cano> GetCanos() {
        List<Cano> canos = new ArrayList<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_CANOS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int tipo = rs.getInt(1);
                int id = rs.getInt(2);
                Cano newCano = new Cano(id);
                newCano.setTipo(tipo);
                canos.add(newCano);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los caños: " + e.getMessage());
        }
        return canos;
    }

    public CustomResponseEntity<Motor> SaveMotor(Motor motor) {
        CustomResponseEntity<Motor> response = new CustomResponseEntity<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_INSERT_MOTOR, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, motor.getNombre());
            stmt.setInt(2, motor.getIdMotor());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        motor.setIdMotor(generatedId); // Actualizar el ID generado
                    }
                }
                response.setBody(motor);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo insertar el motor");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el motor: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Motor GetMotorePorId(int id) {
        Motor Motor = null;
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_MOTOR_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString(3);
                    int motorId = rs.getInt(2);
                    int tipo = rs.getInt(1);
                    Motor = new Motor(motorId);
                    Motor.setNombre(nombre);
                    Motor.setTipo(tipo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el lado de cadena por ID: " + e.getMessage());
        }
        return Motor;
    }

    public List<Motor> GetMotores() {
        List<Motor> motores = new ArrayList<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_MOTORES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString(3);
                int motorId = rs.getInt(2);
                int tipo = rs.getInt(1);
                Motor motor = new Motor(motorId);
                motor.setNombre(nombre);
                motor.setTipo(tipo);
                motores.add(motor);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los motores: " + e.getMessage());
        }
        return motores;
    }


    public CustomResponseEntity<LadoCadena> SaveLadoCadena(LadoCadena ladoCadena) {
        CustomResponseEntity<LadoCadena> response = new CustomResponseEntity<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_INSERT_LADO_CADENA, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ladoCadena.getLado());
            stmt.setInt(2, ladoCadena.getLadoId());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        ladoCadena.setLadoId(generatedId);// Actualizar el ID generado
                    }
                }
                response.setBody(ladoCadena);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo insertar el lado de la cadena");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el lado de la cadena: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public LadoCadena GetLadoCadenaPorId(int id) {
        LadoCadena ladoCadena = null;
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_LADO_CADENA_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String lado = rs.getString(1);
                    int ladoId = rs.getInt(2);
                    ladoCadena = new LadoCadena(ladoId);
                    ladoCadena.setLado(lado);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el lado de cadena por ID: " + e.getMessage());
        }
        return ladoCadena;
    }

    public List<LadoCadena> GetLadosCadenas() {
        List<LadoCadena> ladosCadenas = new ArrayList<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_LADO_CADENAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String lado = rs.getString(1);
                int ladoId = rs.getInt(2);
                LadoCadena cadena = new LadoCadena(ladoId);
                cadena.setLado(lado);
                ladosCadenas.add(cadena);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los lados de cadenas: " + e.getMessage());
        }
        return ladosCadenas;
    }

    public CustomResponseEntity<TipoCadena> SaveTipoCadena(TipoCadena tipoCadena) {
        CustomResponseEntity<TipoCadena> response = new CustomResponseEntity<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_INSERT_TIPO_CADENA, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tipoCadena.getTipoCadena());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int generatedId = rs.getInt(1);
                        tipoCadena.setIdTipoCadena(generatedId);// Actualizar el ID generado
                    }
                }
                response.setBody(tipoCadena);
                response.setStatus(HttpStatus.OK);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMessage("No se pudo insertar el tipo de la cadena");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar el tipo de la cadena: " + e.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public TipoCadena GetTipoCadenaPorId(int id) {
        TipoCadena tipoCadena = null;
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_TIPO_CADENA_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString(2);
                    int tipoid = rs.getInt(1);
                    tipoCadena = new TipoCadena(tipoid);
                    tipoCadena.setTipoCadena(tipo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el lado de cadena por ID: " + e.getMessage());
        }
        return tipoCadena;
    }

    public List<TipoCadena> GetTipoCadenas() {
        List<TipoCadena> tiposCadenas = new ArrayList<>();
        this.conexion = (java.sql.Connection) Conexion.GetConexion();
        try (PreparedStatement stmt = conexion.prepareStatement(SQL_SELECT_TIPO_CADENA);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString(2);
                int tipoid = rs.getInt(1);
                TipoCadena Tipocadena = new TipoCadena(tipoid);
                Tipocadena.setTipoCadena(tipo);
                tiposCadenas.add(Tipocadena);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los lados de cadenas: " + e.getMessage());
        }
        return tiposCadenas;
    }

}
