package controller;

import db.CitaDAO;
import db.PacienteDAO;
import model.Cita;
import model.Paciente;
import view.VistaPaciente;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ControllerPaciente {
    private VistaPaciente vistaPaciente = new VistaPaciente();
    private PacienteDAO pacienteDAO = PacienteDAO.getInstance();
    private CitaDAO citaDAO = CitaDAO.getInstance();

    public void iniciarSesionPaciente() throws SQLException {
        String sip = vistaPaciente.pedirSip(); // Ahora pedimos el SIP
        Paciente paciente = pacienteDAO.getPacienteBySip(sip);

        if (paciente != null) {
            int opcion;
            do {
                opcion = vistaPaciente.mostrarMenuPaciente();
                switch (opcion) {
                    case 1:
                        List<Cita> citas = citaDAO.getCitasByPaciente(Integer.parseInt(sip));
                        vistaPaciente.mostrarCitas(citas);
                        break;
                    case 2:
                        vistaPaciente.mostrarHistorial(paciente);
                        break;
                    case 3:
                        solicitarCita(paciente);
                        break;
                    case 4:
                        vistaPaciente.mostrarMensaje("Volviendo al menú principal...");
                        break;
                    default:
                        vistaPaciente.mostrarMensaje("Opción inválida.");
                }
            } while (opcion != 4);
        } else {
            vistaPaciente.mostrarMensaje("Paciente no encontrado.");
        }
    }

    private void solicitarCita(Paciente paciente) {
        String motivo = vistaPaciente.pedirMotivoCita();
        String fecha = vistaPaciente.pedirFecha(); // formato "YYYY-MM-DD"
        String hora = vistaPaciente.pedirHora();   // formato "HH:MM"

        try {
            int nuevoId = citaDAO.generarNuevoIdCita();
            Cita nuevaCita = new Cita(
                    nuevoId,
                    LocalDateTime.parse(fecha + "T" + hora),
                    motivo,
                    String.valueOf(paciente.getSip()),
                    paciente.getDniDoctor()
            );
            citaDAO.insertCita(nuevaCita);
            vistaPaciente.mostrarMensaje("Cita solicitada correctamente.");
        } catch (Exception e) {
            vistaPaciente.mostrarMensaje("Error al solicitar la cita: " + e.getMessage());
        }
    }
}
