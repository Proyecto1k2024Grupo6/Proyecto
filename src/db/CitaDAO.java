package db;

import model.Cita;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
                    id INT PRIMARY KEY,
                    fecha_cita DATE NOT NULL,
                    hora_cita TIME NOT NULL,
                    motivo_cita VARCHAR(255) NOT NULL,
                    dni_doctor VARCHAR(9) NOT NULL,
                    sip_paciente INT(9) NOT NULL,
                    FOREIGN KEY (dni_doctor) REFERENCES DOCTOR(dni),
                    FOREIGN KEY (sip_paciente) REFERENCES PACIENTE(sip)
                );
            """;

    // Consultas SQL predefinidas para operaciones CRUD
    private static final String INSERT_QUERY = "INSERT INTO CITA (fecha_cita, hora_cita, motivo_cita, dni_doctor, sip_paciente) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM CITA";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM CITA WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE CITA SET fecha_cita = ?, hora_cita = ?, motivo_cita = ?, dni_doctor = ?, sip_paciente = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM CITA WHERE id = ?";

    /**
     * Constructor privado para evitar instanciación externa.
     * Obtiene la conexión a la base de datos desde DBConnection.
     */
    private CitaDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Método estático para obtener la única instancia de CitaDAO.
     *
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
     *
     * @param cita Objeto Cita que se desea insertar.
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL.
     */
    public void insertCita(Cita cita) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, cita.getId());
            statement.setDate(2, Date.valueOf(cita.getFechaHora().toLocalDate())); // Utiliza solo la fecha
            statement.setTime(3, Time.valueOf(cita.getFechaHora().toLocalTime())); // Utiliza solo la hora
            statement.setString(4, cita.getMotivo());
            statement.setString(5, cita.getDniDoctor());
            statement.setString(6, cita.getDniPaciente());
            statement.executeUpdate();
        }
    }

    /**
     * Obtiene todas las citas registradas en la base de datos.
     *
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
     *
     * @param id ID de la cita.
     * @return Objeto Cita si se encuentra, null si no.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Cita getCitaById(int id) throws SQLException {
        Cita cita = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cita = resultSetToCita(resultSet);
            }
        }
        return cita;
    }

    public List<Cita> getCitasDoctor(String dniDoctor) throws SQLException {
        if (dniDoctor == null || dniDoctor.isEmpty()) {
            throw new IllegalArgumentException("El DNI del doctor no puede estar vacío.");
        }

        List<Cita> citas = new ArrayList<>();
        String sql = """
            SELECT * FROM CITA
            WHERE dni_doctor = ?
            ORDER BY fecha_cita, hora_cita
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dniDoctor);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                citas.add(resultSetToCita(resultSet));
            }
        }
        return citas;
    }

    public List<Cita> getCitasByPaciente(int sip) throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM Cita WHERE sip_paciente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sip);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                citas.add(new Cita(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("fechaHora").toLocalDateTime(),
                        resultSet.getString("motivo"),
                        resultSet.getString("sip_paciente"),
                        resultSet.getString("dni_doctor")
                ));
            }
        }
        return citas;
    }

    public int generarNuevoIdCita() throws SQLException {
        String sql = "SELECT MAX(id) FROM Cita";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        }
        return 1;
    }

    /**
     * Actualiza los datos de una cita en la base de datos.
     *
     * @param cita Objeto Cita con los datos actualizados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void updateCita(Cita cita) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setDate(2, Date.valueOf(cita.getFechaHora().toLocalDate())); // Utiliza solo la fecha
            statement.setTime(3, Time.valueOf(cita.getFechaHora().toLocalTime())); // Utiliza solo la hora
            statement.setString(3, cita.getMotivo());
            statement.setString(4, cita.getDniDoctor());
            statement.setString(5, cita.getDniPaciente());
            statement.setInt(6, cita.getId());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina una cita de la base de datos por su ID.
     *
     * @param id ID de la cita a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void deleteCitaById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    /**
     * Convierte un ResultSet en un objeto Cita.
     *
     * @param resultSet Resultado de la consulta SQL.
     * @return Objeto Cita con los datos del ResultSet.
     * @throws SQLException Si ocurre un error en la conversión.
     */
    private Cita resultSetToCita(ResultSet resultSet) throws SQLException {
        LocalDate fecha = resultSet.getDate("fecha_cita").toLocalDate();
        LocalTime hora = resultSet.getTime("hora_cita").toLocalTime();
        LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

        return new Cita(
                resultSet.getInt("id"),
                fechaHora,
                resultSet.getString("motivo_cita"),
                String.valueOf(resultSet.getInt("sip_paciente")),
                resultSet.getString("dni_doctor")
        );
    }
}
