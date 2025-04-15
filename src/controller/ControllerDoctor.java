package controller;

import db.CitaDAO;
import db.DoctorDAO;
import model.Cita;
import model.Doctor;
import view.VistaDoctor;

import java.sql.SQLException;
import java.util.List;

public class ControllerDoctor {
    private VistaDoctor vistaDoctor = new VistaDoctor();
    private DoctorDAO doctorDAO = DoctorDAO.getInstance();
    private CitaDAO citaDAO = CitaDAO.getInstance();

    public void iniciarSesionDoctor() throws SQLException {
        String dni = vistaDoctor.pedirDni();
        Doctor doctor = doctorDAO.getDoctorByDni(dni);

        if (doctor != null) {
            vistaDoctor.mostrarBienvenida(doctor);

            // Cambia esta llamada para obtener todas las citas, no solo las de hoy
            List<Cita> citas = citaDAO.getCitasDoctor(dni);
            vistaDoctor.mostrarCitas(citas);

            boolean continuar = true;
            while (continuar) {
                int opcion = vistaDoctor.mostrarMenuDoctor();
                switch (opcion) {
                    case 1:
                        // Mostrar todas las citas del doctor
                        List<Cita> nuevasCitas = citaDAO.getCitasDoctor(dni);
                        vistaDoctor.mostrarCitas(nuevasCitas);
                        break;
                    case 2:
                        continuar = false;
                        vistaDoctor.mostrarMensaje("Sesión finalizada.");
                        break;
                    default:
                        vistaDoctor.mostrarMensaje("Opción no válida.");
                }
            }

        } else {
            vistaDoctor.mostrarMensaje("Doctor no encontrado.");
        }
    }
}