package db;

import model.Asegurado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase AseguradoDAO, maneja operaciones CRUD para la entidad Asegurado.
 * Cada asegurado es un paciente que tiene una aseguradora asignada.
 *
 * @author Alejandro Rodriguez Blazquez
 * @version 01-2025
 * @since 2025
 */
public class AseguradoDAO {

    private static AseguradoDAO instance;
    private Connection connection;

    public static final String CREATE_TABLE_ASEGURADO = """
        CREATE TABLE IF NOT EXISTS ASEGURADO (
            sip INT(9) PRIMARY KEY,
            aseguradora VARCHAR(100) NOT NULL,
            FOREIGN KEY (sip) REFERENCES PACIENTE(sip)
        );
    """;

    private static final String INSERT_QUERY = "INSERT INTO ASEGURADO (sip, aseguradora) VALUES (?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM ASEGURADO";
    private static final String SELECT_BY_SIP_QUERY = "SELECT * FROM ASEGURADO WHERE sip = ?";
    private static final String UPDATE_QUERY = "UPDATE ASEGURADO SET aseguradora = ? WHERE sip = ?";
    private static final String DELETE_QUERY = "DELETE FROM ASEGURADO WHERE sip = ?";

    private AseguradoDAO() {
        this.connection = DBConnection.getConnection();
    }

    public static synchronized AseguradoDAO getInstance() {
        if (instance == null) {
            instance = new AseguradoDAO();
        }
        return instance;
    }

    public void insertAsegurado(Asegurado asegurado) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, asegurado.getSip());
            statement.setString(2, asegurado.getAseguradora());
            statement.executeUpdate();
        }
    }

    public List<Asegurado> getAllAsegurados() throws SQLException {
        List<Asegurado> asegurados = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                asegurados.add(resultSetToAsegurado(resultSet));
            }
        }
        return asegurados;
    }

    public Optional<Asegurado> getAseguradoBySip(int sip) throws SQLException {
        Optional<Asegurado> asegurado = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_SIP_QUERY)) {
            statement.setInt(1, sip);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                asegurado = Optional.of(resultSetToAsegurado(resultSet));
            }
        }
        return asegurado;
    }

    public void updateAsegurado(Asegurado asegurado) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, asegurado.getAseguradora());
            statement.setInt(2, asegurado.getSip());
            statement.executeUpdate();
        }
    }

    public void deleteAseguradoBySip(int sip) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, sip);
            statement.executeUpdate();
        }
    }

    private Asegurado resultSetToAsegurado(ResultSet resultSet) throws SQLException {
        return new Asegurado(
                resultSet.getInt("sip"),
                resultSet.getString("aseguradora")
        );
    }
}

