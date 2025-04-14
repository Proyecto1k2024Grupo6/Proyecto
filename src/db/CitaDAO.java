package db;

import model.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase CitaDAO. Gestiona las operaciones CRUD de la tabla Cita en la base de datos
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class CitaDAO {
    private Connection connection;

    /**
     * Constructor. Inicializa la conexión con la base de datos.
     */
    public CitaDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Inserta una nueva cita médica en la base de datos.
     * @param cita Objeto Cita que se desea insertar
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL
     */
    public void insertarCita(Cita cita) throws SQLException {
        String sql = "INSERT INTO Cita (idCita, fechaHora, motivo, dniPaciente, dniDoctor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cita.getIdCita());
            stmt.setTimestamp(2, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(3, cita.getMotivo());
            stmt.setString(4, cita.getDniPaciente());
            stmt.setString(5, cita.getDniDoctor());
            stmt.executeUpdate();
        }
    }

    /**
     * Recupera todas las citas registradas en la base de datos.
     * @return Lista de objetos Cita
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL
     */
    public List<Cita> obtenerTodasLasCitas() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM Cita";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("idCita"),
                        rs.getTimestamp("fechaHora").toLocalDateTime(),
                        rs.getString("motivo"),
                        rs.getString("dniPaciente"),
                        rs.getString("dniDoctor")
                );
                citas.add(cita);
            }
        }
        return citas;
    }

    /**
     * Actualiza la información de una cita existente en la base de datos.
     * @param cita Objeto Cita con los nuevos datos
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL
     */
    public void actualizarCita(Cita cita) throws SQLException {
        String sql = "UPDATE Cita SET fechaHora = ?, motivo = ?, dniPaciente = ?, dniDoctor = ? WHERE idCita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setString(3, cita.getDniPaciente());
            stmt.setString(4, cita.getDniDoctor());
            stmt.setInt(5, cita.getIdCita());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una cita de la base de datos según su ID.
     * @param idCita ID de la cita a eliminar
     * @throws SQLException Si ocurre un error al ejecutar la sentencia SQL
     */
    public void eliminarCita(int idCita) throws SQLException {
        String sql = "DELETE FROM Cita WHERE idCita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCita);
            stmt.executeUpdate();
        }
    }
}
