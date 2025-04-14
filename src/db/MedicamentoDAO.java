package db;

import model.Medicamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase MedicamentoDAO. Controlador para manejar operaciones CRUD de Medicamento en la base de datos
 * @author Todos
 * @version 01-2025
 * @since 2025
 */
public class MedicamentoDAO {
    private Connection connection;

    public MedicamentoDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Inserta un medicamento en la base de datos.
     * @param medicamento Medicamento a insertar
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void insertarMedicamento(Medicamento medicamento) throws SQLException {
        String sql = "INSERT INTO Medicamento (id, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medicamento.getId());
            stmt.setString(2, medicamento.getNombre());
            stmt.setString(3, medicamento.getDescripcion());
            stmt.executeUpdate();
        }
    }

    /**
     * Recupera todos los medicamentos de la base de datos.
     * @return Lista de medicamentos
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public List<Medicamento> obtenerTodos() throws SQLException {
        List<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Medicamento m = new Medicamento(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
                lista.add(m);
            }
        }
        return lista;
    }

    /**
     * Actualiza un medicamento en la base de datos.
     * @param medicamento Medicamento con los datos actualizados
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void actualizarMedicamento(Medicamento medicamento) throws SQLException {
        String sql = "UPDATE Medicamento SET nombre = ?, descripcion = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medicamento.getNombre());
            stmt.setString(2, medicamento.getDescripcion());
            stmt.setString(3, medicamento.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un medicamento por su ID.
     * @param id ID del medicamento
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void eliminarMedicamento(String id) throws SQLException {
        String sql = "DELETE FROM Medicamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
}