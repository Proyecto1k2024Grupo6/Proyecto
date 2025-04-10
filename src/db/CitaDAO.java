package db;

import model.Cita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    private Connection connection;

    public CitaDAO() {
        this.connection = DBConnection.getConnection();
    }

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

    public void eliminarCita(int idCita) throws SQLException {
        String sql = "DELETE FROM Cita WHERE idCita = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCita);
            stmt.executeUpdate();
        }
    }
}
