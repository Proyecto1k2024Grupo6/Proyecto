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
        // Pedimos el DNI del doctor
        String dni = vistaDoctor.pedirDni();
        // Buscamos al doctor en la base de datos
        Doctor doctor = doctorDAO.getDoctorByDni(dni);

        if (doctor != null) {
            // Mostramos mensaje de bienvenida
            vistaDoctor.mostrarBienvenida(doctor);

            // Obtenemos las citas del doctor para hoy
            List<Cita> citasHoy = citaDAO.getCitasDoctor(dni);

            // Mostramos las citas (o mensaje si no hay citas)
            vistaDoctor.mostrarCitas(citasHoy);

            // Menú de opciones
            boolean continuar = true;
            while (continuar) {
                int opcion = vistaDoctor.mostrarMenuDoctor();
                switch (opcion) {
                    case 1:
                        // Mostrar las citas de hoy nuevamente
                        List<Cita> nuevasCitas = citaDAO.getCitasDoctor(dni);
                        vistaDoctor.mostrarCitas(nuevasCitas);
                        break;
                    case 2:
                        // Cerrar sesión
                        continuar = false;
                        vistaDoctor.mostrarMensaje("Sesión finalizada.");
                        break;
                    default:
                        // Opción inválida
                        vistaDoctor.mostrarMensaje("Opción no válida.");
                }
            }
        } else {
            // Si no se encuentra el doctor
            vistaDoctor.mostrarMensaje("Doctor no encontrado.");
        }
    }
}
