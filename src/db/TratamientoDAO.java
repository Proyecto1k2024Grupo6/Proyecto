package db;

/**
 * DAO para la clase Tratamiento.
 * Permite realizar operaciones CRUD sobre la tabla Tratamiento.
 * @author Sergio Zambrana
 */
import model.Tratamiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAO {
    private Connection conexion;

    public TratamientoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un tratamiento en la base de datos.
     * @param t El tratamiento a insertar.
     * @throws SQLException si ocurre un error al insertar.
     */
    public void insertar(Tratamiento t) throws SQLException {
        String sql = "INSERT INTO Tratamiento (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, t.getNombre());
            stmt.setString(2, t.getDescripcion());
            stmt.executeUpdate();
        }
    }

    /**
     * Obtiene todos los tratamientos de la base de datos.
     * @return Lista de tratamientos.
     * @throws SQLException si ocurre un error al consultar.
     */
    public List<Tratamiento> obtenerTodos() throws SQLException {
        List<Tratamiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tratamiento";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Tratamiento t = new Tratamiento(
                        rs.getInt("idTratamiento"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
                lista.add(t);
            }
        }
        return lista;
    }

    /**
     * Actualiza un tratamiento existente en la base de datos.
     * @param t El tratamiento actualizado.
     * @throws SQLException si ocurre un error al actualizar.
     */
    public void actualizar(Tratamiento t) throws SQLException {
        String sql = "UPDATE Tratamiento SET nombre=?, descripcion=? WHERE idTratamiento=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, t.getNombre());
            stmt.setString(2, t.getDescripcion());
            stmt.setInt(3, t.getIdTratamiento());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina un tratamiento de la base de datos por su ID.
     * @param idTratamiento ID del tratamiento a eliminar.
     * @throws SQLException si ocurre un error al eliminar.
     */
    public void eliminar(int idTratamiento) throws SQLException {
        String sql = "DELETE FROM Tratamiento WHERE idTratamiento=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idTratamiento);
            stmt.executeUpdate();
        }
    }
}
