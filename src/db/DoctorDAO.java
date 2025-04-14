package db;

import model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DoctorDAO, maneja las operaciones CRUD para la entidad Doctor.
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class DoctorDAO {
    private Connection connection;

    /**
     * Constructor. Inicializa la conexión a la base de datos.
     */
    public DoctorDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Inserta un nuevo doctor en la base de datos.
     * @param doctor Objeto Doctor que se desea insertar
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL
     */
    public void insertarDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO Doctor (dni, nombre, email, especialidad, telefono) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doctor.getDni());
            stmt.setString(2, doctor.getNombre());
            stmt.setString(3, doctor.getEmail());
            stmt.setString(4, doctor.getEspecialidad());
            stmt.setString(5, doctor.getTelefono());
            stmt.executeUpdate();
        }
    }

    /**
     * Recupera todos los doctores de la base de datos.
     * @return Lista de doctores
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL
     */
    public List<Doctor> obtenerTodosLosDoctores() throws SQLException {
        List<Doctor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Doctor";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("especialidad"),
                        rs.getString("telefono")
                );
                lista.add(d);
            }
        }
        return lista;
    }

    /**
     * Actualiza la información de un doctor existente.
     * @param doctor Objeto Doctor con los nuevos datos
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL
     */
    public void actualizarDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE Doctor SET nombre = ?, email = ?, especialidad = ?, telefono = ? WHERE dni = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doctor.getNombre());
            stmt.setString(2, doctor.getEmail());
            stmt.setString(3, doctor.getEspecialidad());
            stmt.setString(4, doctor.getTelefono());
            stmt.setString(5, doctor.getDni());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un doctor de la base de datos según su DNI.
     * @param dni DNI del doctor a eliminar
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL
     */
    public void eliminarDoctor(String dni) throws SQLException {
        String sql = "DELETE FROM Doctor WHERE dni = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.executeUpdate();
        }
    }
}
