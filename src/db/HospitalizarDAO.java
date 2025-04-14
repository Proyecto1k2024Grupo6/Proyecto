package db;

import model.Hospitalizar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para manejar operaciones CRUD de la relación Hospitalizar (doctor asignado a hospitalización)
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class HospitalizarDAO {
    private Connection connection;

    /**
     * Constructor. Establece la conexión a la base de datos
     */
    public HospitalizarDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Inserta una nueva relación Hospitalizar.
     * @param h Objeto Hospitalizar a insertar
     * @throws SQLException Si ocurre un error en la consulta
     */
    public void insertar(Hospitalizar h) throws SQLException {
        String query = "INSERT INTO Hospitalizar (idHospitalizacion, dniDoctor) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, h.getIdHospitalizacion());
            stmt.setString(2, h.getDniDoctor());
            stmt.executeUpdate();
        }
    }

    /**
     * Recupera todas las relaciones Hospitalizar.
     * @return Lista de relaciones hospitalización-doctor
     * @throws SQLException Si hay un problema al acceder a la base de datos
     */
    public List<Hospitalizar> seleccionarTodas() throws SQLException {
        List<Hospitalizar> lista = new ArrayList<>();
        String query = "SELECT * FROM Hospitalizar";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Hospitalizar(
                        rs.getInt("idHospitalizacion"),
                        rs.getString("dniDoctor")
                ));
            }
        }
        return lista;
    }

    /**
     * Actualiza una relación Hospitalizar.
     * @param h Objeto Hospitalizar con los nuevos datos
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void actualizar(Hospitalizar h) throws SQLException {
        String query = "UPDATE Hospitalizar SET dniDoctor = ? WHERE idHospitalizacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, h.getDniDoctor());
            stmt.setInt(2, h.getIdHospitalizacion());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una relación Hospitalizar según el ID de hospitalización.
     * @param id ID de hospitalización
     * @throws SQLException Si ocurre un error al eliminar
     */
    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM Hospitalizar WHERE idHospitalizacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

