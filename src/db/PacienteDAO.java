package db;
import model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase PacienteDAO, maneja las operaciones CRUD para la entidad Paciente.
 * Utiliza patrón singleton y conexión centralizada desde DBConnection.
 *
 * @author Alejandro Rodriguez Blazquez
 * @version 01-2025
 * @since 2025
 */
public class PacienteDAO {

    // Instancia única de PacienteDAO
    private static PacienteDAO instance;
    // Conexión a la base de datos
    private Connection connection;

    // Sentencia SQL para crear la tabla PACIENTE si no existe
    public static final String CREATE_TABLE_PACIENTE = """
        CREATE TABLE IF NOT EXISTS PACIENTE (
            sip INT(9) PRIMARY KEY,
            nombre VARCHAR(50),
            fnac DATE,
            telefono VARCHAR(15),
            dni VARCHAR(9),
            dni_doctor VARCHAR(9),
            FOREIGN KEY (dni_doctor) REFERENCES DOCTOR(dni)
        );
    """;

    // Consultas SQL predefinidas
    private static final String INSERT_QUERY = "INSERT INTO PACIENTE (sip, nombre, fnac, telefono, dni, dni_doctor) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM PACIENTE";
    private static final String SELECT_BY_SIP_QUERY = "SELECT * FROM PACIENTE WHERE sip = ?";
    private static final String UPDATE_QUERY = "UPDATE PACIENTE SET nombre = ?, fnac = ?, telefono = ?, dni = ?, dni_doctor = ? WHERE sip = ?";
    private static final String DELETE_QUERY = "DELETE FROM PACIENTE WHERE sip = ?";

    /**
     * Constructor privado que obtiene la conexión desde DBConnection.
     */
    private PacienteDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Devuelve la única instancia de PacienteDAO.
     * @return instancia singleton de PacienteDAO.
     */
    public static synchronized PacienteDAO getInstance() {
        if (instance == null) {
            instance = new PacienteDAO();
        }
        return instance;
    }

    /**
     * Inserta un nuevo paciente en la base de datos.
     * @param paciente Objeto Paciente a insertar.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void insertPaciente(Paciente paciente) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setInt(1, paciente.getSip());
            statement.setString(2, paciente.getNombre());
            statement.setDate(3, Date.valueOf(paciente.getFnac()));
            statement.setString(4, paciente.getTelefono());
            statement.setString(5, paciente.getDni());
            statement.setString(6, paciente.getDniDoctor());
            statement.executeUpdate();
        }
    }

    /**
     * Obtiene una lista de todos los pacientes.
     * @return Lista de objetos Paciente.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<Paciente> getAllPacientes() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pacientes.add(resultSetToPaciente(resultSet));
            }
        }
        return pacientes;
    }

    /**
     * Busca un paciente por su SIP.
     * @param sip Código SIP del paciente.
     * @return Optional con el objeto Paciente si existe.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public Paciente getPacienteBySip(String sip) throws SQLException {
        String sql = "SELECT * FROM PACIENTE WHERE sip = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sip);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Paciente(
                        resultSet.getInt("sip"),
                        resultSet.getString("nombre"),
                        resultSet.getString("fnac"),
                        resultSet.getString("telefono"),
                        resultSet.getString("dni"),
                        resultSet.getString("dni_doctor")
                );
            }
        }
        return null;
    }
    public Paciente getPacienteByDni(String dni) throws SQLException {
        Paciente paciente = null;
        String sql = "SELECT * FROM PACIENTE WHERE dni = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente(
                        rs.getInt("sip"),
                        rs.getString("nombre"),
                        rs.getString("fnac"),
                        rs.getString("telefono"),
                        rs.getString("dni"),
                        rs.getString("dni_doctor")
                );
            }
        }
        return paciente;
    }

    /**
     * Actualiza los datos de un paciente.
     * @param paciente Objeto Paciente con los datos actualizados.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void updatePaciente(Paciente paciente) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, paciente.getNombre());
            statement.setDate(2, Date.valueOf(paciente.getFnac()));
            statement.setString(3, paciente.getTelefono());
            statement.setString(4, paciente.getDni());
            statement.setString(5, paciente.getDniDoctor());
            statement.setInt(6, paciente.getSip());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina un paciente por su SIP.
     * @param sip Código SIP del paciente a eliminar.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public void deletePacienteBySip(int sip) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, sip);
            statement.executeUpdate();
        }
    }

    /**
     * Convierte un ResultSet a un objeto Paciente.
     * @param resultSet Resultado de la consulta.
     * @return Objeto Paciente con los datos del ResultSet.
     * @throws SQLException Si ocurre un error al leer datos.
     */
    private Paciente resultSetToPaciente(ResultSet resultSet) throws SQLException {
        return new Paciente(
                resultSet.getInt("sip"),
                resultSet.getString("nombre"),
                resultSet.getDate("fnac").toString(),
                resultSet.getString("telefono"),
                resultSet.getString("dni"),
                resultSet.getString("dni_doctor")
        );
    }
}


