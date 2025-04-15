package db;

import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase DoctorDAO, maneja las operaciones CRUD para la entidad Doctor.
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class DoctorDAO {

    // Instancia única de DoctorDAO
    private static DoctorDAO instance;
    // Conexión a la base de datos
    private Connection connection;

    // Sentencia SQL para crear la tabla Doctor si no existe
    public static final String CREATE_TABLE_DOCTOR = """
        CREATE TABLE IF NOT EXISTS DOCTOR (
            dni VARCHAR(9) PRIMARY KEY,
            nombre VARCHAR(100) NOT NULL,
            especialidad VARCHAR(100) NOT NULL,
            telefono VARCHAR(15),
            email VARCHAR(100) UNIQUE
        );
    """;

    // Consultas SQL predefinidas para operaciones CRUD
    private static final String INSERT_QUERY = "INSERT INTO DOCTOR (dni, nombre, especialidad, telefono, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM DOCTOR";
    private static final String SELECT_BY_DNI_QUERY = "SELECT * FROM DOCTOR WHERE dni = ?";
    private static final String UPDATE_QUERY = "UPDATE DOCTOR SET nombre = ?, especialidad = ?, telefono = ?, email = ? WHERE dni = ?";
    private static final String DELETE_QUERY = "DELETE FROM DOCTOR WHERE dni = ?";

    /**
     * Constructor privado para evitar instanciación externa.
     * Obtiene la conexión a la base de datos desde DBConnection.
     */
    private DoctorDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Método estático para obtener la única instancia de DoctorDAO.
     * @return instancia única de DoctorDAO.
     */
    public static synchronized DoctorDAO getInstance() {
        if (instance == null) {
            instance = new DoctorDAO();
        }
        return instance;
    }

    /**
     * Inserta un nuevo doctor en la base de datos.
     * @param doctor Objeto Doctor a insertar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void insertDoctor(Doctor doctor) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, doctor.getDni());
            statement.setString(2, doctor.getNombre());
            statement.setString(3, doctor.getEspecialidad());
            statement.setString(4, doctor.getTelefono());
            statement.setString(5, doctor.getEmail());
            statement.executeUpdate();
        }
    }

    /**
     * Obtiene todos los doctores almacenados en la base de datos.
     * @return Lista de objetos Doctor.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                doctors.add(resultSetToDoctor(resultSet));
            }
        }
        return doctors;
    }

    /**
     * Obtiene un doctor a partir de su DNI .
     * @param dni Identificador único del doctor.
     * @return Objeto Doctor si se encuentra, null si no.
     */
    public Doctor getDoctorByDniDirect(String dni) {
        Doctor doctor = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_DNI_QUERY)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                doctor = resultSetToDoctor(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
    public Doctor getDoctorByDni(String dni) throws SQLException {
        Doctor doctor = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_DNI_QUERY)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                doctor = resultSetToDoctor(resultSet);
            }
        }
        return doctor;
    }
    /**
     * Actualiza los datos de un doctor en la base de datos.
     * @param doctor Objeto Doctor con los datos actualizados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void updateDoctor(Doctor doctor) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, doctor.getNombre());
            statement.setString(2, doctor.getEspecialidad());
            statement.setString(3, doctor.getTelefono());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getDni());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina un doctor de la base de datos por su DNI.
     * @param dni Identificador único del doctor a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void deleteDoctorByDni(String dni) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }

    /**
     * Convierte un ResultSet en un objeto Doctor.
     * @param resultSet Resultado de la consulta SQL.
     * @return Objeto Doctor con los datos del ResultSet.
     * @throws SQLException Si ocurre un error en la conversión.
     */
    private Doctor resultSetToDoctor(ResultSet resultSet) throws SQLException {
        return new Doctor(
                resultSet.getString("dni"),
                resultSet.getString("nombre"),
                resultSet.getString("email"),
                resultSet.getString("especialidad"),
                resultSet.getString("telefono"));
    }
}
