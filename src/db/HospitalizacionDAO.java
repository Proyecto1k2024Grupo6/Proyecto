package db;

import db.DBConnection;
import model.Hospitalizacion;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para manejar operaciones CRUD de la entidad Hospitalizacion
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class HospitalizacionDAO {
    private Connection connection;

    /**
     * Constructor. Establece la conexión a la base de datos.
     */
    public HospitalizacionDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Inserta una nueva hospitalización.
     * @param h Objeto Hospitalizacion que se desea guardar
     * @throws SQLException Si ocurre un error en la base de datos
     */
    public void insertar(Hospitalizacion h) throws SQLException {
        String query = "INSERT INTO Hospitalizacion (idHospitalizacion, dniPaciente, fechaIngreso, fechaAlta) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, h.getIdHospitalizacion());
            stmt.setString(2, h.getDniPaciente());
            stmt.setDate(3, Date.valueOf(h.getFechaIngreso()));
            stmt.setDate(4, Date.valueOf(h.getFechaAlta()));
            stmt.executeUpdate();
        }
    }

    /**
     * Recupera todas las hospitalizaciones de la base de datos.
     * @return Lista de hospitalizaciones
     * @throws SQLException Si ocurre un error en la consulta
     */
    public List<Hospitalizacion> seleccionarTodas() throws SQLException {
        List<Hospitalizacion> lista = new ArrayList<>();
        String query = "SELECT * FROM Hospitalizacion";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Hospitalizacion(
                        rs.getInt("idHospitalizacion"),
                        rs.getString("dniPaciente"),
                        rs.getDate("fechaIngreso").toLocalDate(),
                        rs.getDate("fechaAlta").toLocalDate()
                ));
            }
        }
        return lista;
    }

    /**
     * Actualiza una hospitalización existente.
     * @param h Objeto Hospitalizacion con los nuevos datos
     * @throws SQLException Si ocurre un error al actualizar
     */
    public void actualizar(Hospitalizacion h) throws SQLException {
        String query = "UPDATE Hospitalizacion SET dniPaciente = ?, fechaIngreso = ?, fechaAlta = ? WHERE idHospitalizacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, h.getDniPaciente());
            stmt.setDate(2, Date.valueOf(h.getFechaIngreso()));
            stmt.setDate(3, Date.valueOf(h.getFechaAlta()));
            stmt.setInt(4, h.getIdHospitalizacion());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una hospitalización por su ID.
     * @param id ID de la hospitalización a eliminar
     * @throws SQLException Si ocurre un error al eliminar
     */
    public void eliminar(int id) throws SQLException {
        String query = "DELETE FROM Hospitalizacion WHERE idHospitalizacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}