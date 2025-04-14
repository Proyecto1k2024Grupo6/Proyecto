package db;

/**
 * DAO para la clase Recibir.
 * Gestiona la relación entre pacientes y tratamientos en la base de datos.
 * Cada paciente puede recibir varios tratamientos.
 * Relación con clave compuesta (idPaciente, idTratamiento).
 * @author Sergio Zambrana
 */
import model.Recibir;
import java.sql.*;
import java.time.LocalDate;

public class RecibirDAO {
    private Connection conexion;

    public RecibirDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una relación de tratamiento recibido por un paciente.
     * @param r La instancia de Recibir a insertar.
     * @throws SQLException si ocurre un error al insertar.
     */
    public void insertar(Recibir r) throws SQLException {
        String sql = "INSERT INTO Recibir (idPaciente, idTratamiento, fechaInicio, fechaFin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, r.getIdPaciente());
            stmt.setInt(2, r.getIdTratamiento());
            stmt.setDate(3, Date.valueOf(r.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(r.getFechaFin()));
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una relación Recibir de la base de datos.
     * @param idPaciente ID del paciente.
     * @param idTratamiento ID del tratamiento.
     * @throws SQLException si ocurre un error al eliminar.
     */
    public void eliminar(int idPaciente, int idTratamiento) throws SQLException {
        String sql = "DELETE FROM Recibir WHERE idPaciente=? AND idTratamiento=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            stmt.setInt(2, idTratamiento);
            stmt.executeUpdate();
        }
    }
}

