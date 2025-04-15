package db;

import model.NoAsegurado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase NoAseguradoDAO, maneja operaciones CRUD para la entidad NoAsegurado.
 * Cada no asegurado también es un paciente, pero no tiene aseguradora asociada.
 *
 * @author Alejandro Rodriguez Blazquez
 * @version 01-2025
 * @since 2025
 */
public class NoAseguradoDAO {

    private static NoAseguradoDAO instance;
    private Connection connection;

    public static final String CREATE_TABLE_NO_ASEGURADO = """
        CREATE TABLE IF NOT EXISTS NO_ASEGURADO (
            sip INT(9) PRIMARY KEY,
            FOREIGN KEY (sip) REFERENCES PACIENTE(sip)
        );
    """;

    private static final String INSERT_QUERY = "INSERT INTO NO_ASEGURADO (sip) VALUES (?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM NO_ASEGURADO";
    private static final String SELECT_BY_SIP_QUERY = "SELECT * FROM NO_ASEGURADO WHERE sip = ?";
    private static final String DELETE_QUERY = "DELETE FROM NO_ASEGURADO WHERE sip = ?";

    private NoAseguradoDAO() {
        this.connection = DBConnection.getConnection();
    }

    public static synchronized NoAseguradoDAO getInstance() {
        if (instance == null) {
            instance = new NoAseguradoDAO();
        }
        return instance;
    }

    public void insertNoAsegurado(NoAsegurado noAsegurado) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, noAsegurado.getSip());
            statement.executeUpdate();
        }
    }

    public List<NoAsegurado> getAllNoAsegurados() throws SQLException {
        List<NoAsegurado> noAsegurados = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                noAsegurados.add(new NoAsegurado(resultSet.getInt("sip")));
            }
        }
        return noAsegurados;
    }

    public Optional<NoAsegurado> getNoAseguradoBySip(int sip) throws SQLException {
        Optional<NoAsegurado> noAsegurado = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_SIP_QUERY)) {
            statement.setInt(1, sip);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                noAsegurado = Optional.of(new NoAsegurado(resultSet.getInt("sip")));
            }
        }
        return noAsegurado;
    }

    public void deleteNoAseguradoBySip(int sip) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, sip);
            statement.executeUpdate();
        }
    }
}

