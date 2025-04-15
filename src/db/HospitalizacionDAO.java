package db;

import db.DBConnection;
import model.Hospitalizacion;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para manejar operaciones CRUD de la entidad Hospitalizacion
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class HospitalizacionDAO {

    // Instancia única de HospitalizacionDAO
    private static HospitalizacionDAO instance;
    // Conexión a la base de datos
    private Connection connection;

    // Consultas SQL predefinidas para operaciones CRUD
    private static final String INSERT_QUERY = "INSERT INTO Hospitalizacion (idHospitalizacion, dniPaciente, fechaIngreso, fechaAlta) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Hospitalizacion";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Hospitalizacion WHERE idHospitalizacion = ?";
    private static final String UPDATE_QUERY = "UPDATE Hospitalizacion SET dniPaciente = ?, fechaIngreso = ?, fechaAlta = ? WHERE idHospitalizacion = ?";
    private static final String DELETE_QUERY = "DELETE FROM Hospitalizacion WHERE idHospitalizacion = ?";

    /**
     * Constructor privado para evitar instanciación externa.
     * Obtiene la conexión a la base de datos desde DBConnection.
     */
    private HospitalizacionDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Método estático para obtener la única instancia de HospitalizacionDAO.
     * @return instancia única de HospitalizacionDAO.
     */
    public static synchronized HospitalizacionDAO getInstance() {
        if (instance == null) {
            instance = new HospitalizacionDAO();
        }
        return instance;
    }

    /**
     * Inserta una nueva hospitalización en la base de datos.
     * @param hospitalizacion Objeto Hospitalizacion que se desea insertar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void insertar(Hospitalizacion hospitalizacion) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, hospitalizacion.getIdHospitalizacion());
            statement.setString(2, hospitalizacion.getDniPaciente());
            statement.setDate(3, Date.valueOf(hospitalizacion.getFechaIngreso()));
            statement.setDate(4, Date.valueOf(hospitalizacion.getFechaAlta()));
            statement.executeUpdate();
        }
    }

    /**
     * Obtiene todas las hospitalizaciones registradas en la base de datos.
     * @return Lista de objetos Hospitalizacion.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    public List<Hospitalizacion> obtenerTodasLasHospitalizaciones() throws SQLException {
        List<Hospitalizacion> hospitalizaciones = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                hospitalizaciones.add(resultSetToHospitalizacion(resultSet));
            }
        }
        return hospitalizaciones;
    }

    /**
     * Obtiene una hospitalización a partir de su ID.
     * @param id ID de la hospitalización.
     * @return Objeto Hospitalizacion si se encuentra, null si no.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Hospitalizacion obtenerHospitalizacionPorId(int id) throws SQLException {
        Hospitalizacion hospitalizacion = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hospitalizacion = resultSetToHospitalizacion(resultSet);
            }
        }
        return hospitalizacion;
    }

    /**
     * Actualiza los datos de una hospitalización en la base de datos.
     * @param hospitalizacion Objeto Hospitalizacion con los datos actualizados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void actualizar(Hospitalizacion hospitalizacion) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, hospitalizacion.getDniPaciente());
            statement.setDate(2, Date.valueOf(hospitalizacion.getFechaIngreso()));
            statement.setDate(3, Date.valueOf(hospitalizacion.getFechaAlta()));
            statement.setInt(4, hospitalizacion.getIdHospitalizacion());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina una hospitalización de la base de datos por su ID.
     * @param id ID de la hospitalización a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void eliminarPorId(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    /**
     * Convierte un ResultSet en un objeto Hospitalizacion.
     * @param resultSet Resultado de la consulta SQL.
     * @return Objeto Hospitalizacion con los datos del ResultSet.
     * @throws SQLException Si ocurre un error en la conversión.
     */
    private Hospitalizacion resultSetToHospitalizacion(ResultSet resultSet) throws SQLException {
        return new Hospitalizacion(
                resultSet.getInt("idHospitalizacion"),
                resultSet.getString("dniPaciente"),
                resultSet.getDate("fechaIngreso").toLocalDate(),
                resultSet.getDate("fechaAlta").toLocalDate());
    }
}