package db;

import model.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase CitaDAO. Gestiona las operaciones CRUD de la tabla Cita en la base de datos
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class CitaDAO {

    // Instancia única de CitaDAO
    private static CitaDAO instance;
    // Conexión a la base de datos
    private Connection connection;

    // Sentencia SQL para crear la tabla Cita si no existe
    public static final String CREATE_TABLE_CITA = """
        CREATE TABLE IF NOT EXISTS Cita (
            idCita INT PRIMARY KEY,
            fechaHora TIMESTAMP NOT NULL,
            motivo VARCHAR(255) NOT NULL,
            dniPaciente VARCHAR(9) NOT NULL,
            dniDoctor VARCHAR(9) NOT NULL,
            FOREIGN KEY (dniPaciente) REFERENCES Persona(dni),
            FOREIGN KEY (dniDoctor) REFERENCES Doctor(dni)
        );
    """;

    // Consultas SQL predefinidas para operaciones CRUD
    private static final String INSERT_QUERY = "INSERT INTO Cita (idCita, fechaHora, motivo, dniPaciente, dniDoctor) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Cita";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Cita WHERE idCita = ?";
    private static final String UPDATE_QUERY = "UPDATE Cita SET fechaHora = ?, motivo = ?, dniPaciente = ?, dniDoctor = ? WHERE idCita = ?";
    private static final String DELETE_QUERY = "DELETE FROM Cita WHERE idCita = ?";

    /**
     * Constructor privado para evitar instanciación externa.
     * Obtiene la conexión a la base de datos desde DBConnection.
     */
    private CitaDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Método estático para obtener la única instancia de CitaDAO.
     * @return instancia única de CitaDAO.
     */
    public static synchronized CitaDAO getInstance() {
        if (instance == null) {
            instance = new CitaDAO();
        }
        return instance;
    }

    /**
     * Inserta una nueva cita médica en la base de datos.
     * @param cita Objeto Cita que se desea insertar.
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL.
     */
    public void insertCita(Cita cita) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, cita.getIdCita());
            statement.setTimestamp(2, Timestamp.valueOf(cita.getFechaHora()));
            statement.setString(3, cita.getMotivo());
            statement.setString(4, cita.getDniPaciente());
            statement.setString(5, cita.getDniDoctor());
            statement.executeUpdate();
        }
    }

    /**
     * Obtiene todas las citas registradas en la base de datos.
     * @return Lista de objetos Cita.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    public List<Cita> getAllCitas() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                citas.add(resultSetToCita(resultSet));
            }
        }
        return citas;
    }

    /**
     * Obtiene una cita a partir de su ID.
     * @param idCita ID de la cita.
     * @return Objeto Cita si se encuentra, null si no.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Optional<Cita> getCitaById(int idCita) throws SQLException {
        Optional<Cita> cita = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, idCita);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cita = Optional.of(resultSetToCita(resultSet));
            }
        }
        return cita;
    }

    /**
     * Actualiza los datos de una cita en la base de datos.
     * @param cita Objeto Cita con los datos actualizados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void updateCita(Cita cita) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            statement.setString(2, cita.getMotivo());
            statement.setString(3, cita.getDniPaciente());
            statement.setString(4, cita.getDniDoctor());
            statement.setInt(5, cita.getIdCita());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina una cita de la base de datos por su ID.
     * @param idCita ID de la cita a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void deleteCitaById(int idCita) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, idCita);
            statement.executeUpdate();
        }
    }

    /**
     * Convierte un ResultSet en un objeto Cita.
     * @param resultSet Resultado de la consulta SQL.
     * @return Objeto Cita con los datos del ResultSet.
     * @throws SQLException Si ocurre un error en la conversión.
     */
    private Cita resultSetToCita(ResultSet resultSet) throws SQLException {
        return new Cita(
                resultSet.getInt("idCita"),
                resultSet.getTimestamp("fechaHora").toLocalDateTime(),
                resultSet.getString("motivo"),
                resultSet.getString("dniPaciente"),
                resultSet.getString("dniDoctor"));
    }
}
