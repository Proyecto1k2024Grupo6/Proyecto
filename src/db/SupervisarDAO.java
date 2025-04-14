package db;

/**
 * DAO para la clase Supervisar.
 * Representa la relación entre doctores y tratamientos supervisados.
 * Relación con clave compuesta (idDoctor, idTratamiento).
 * Permite insertar y eliminar relaciones de supervisión.
 * @author Sergio Zambrana
 */
import model.Supervisar;
import java.sql.*;

public class SupervisarDAO {
    private Connection conexion;

    public SupervisarDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta una relación de supervisión de tratamiento por un doctor.
     * @param s La instancia de Supervisar a insertar.
     * @throws SQLException si ocurre un error al insertar.
     */
    public void insertar(Supervisar s) throws SQLException {
        String sql = "INSERT INTO Supervisar (idDoctor, idTratamiento, observaciones) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, s.getIdDoctor());
            stmt.setInt(2, s.getIdTratamiento());
            stmt.setString(3, s.getObservaciones());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una relación de supervisión.
     * @param idDoctor ID del doctor.
     * @param idTratamiento ID del tratamiento.
     * @throws SQLException si ocurre un error al eliminar.
     */
    public void eliminar(int idDoctor, int idTratamiento) throws SQLException {
        String sql = "DELETE FROM Supervisar WHERE idDoctor=? AND idTratamiento=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idDoctor);
            stmt.setInt(2, idTratamiento);
            stmt.executeUpdate();
        }
    }
}
