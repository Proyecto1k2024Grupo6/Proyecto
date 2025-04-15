package view;

import model.Cita;
import model.Paciente;

import java.util.List;
import java.util.Scanner;

public class VistaPaciente {
    private Scanner scanner = new Scanner(System.in);

    public String pedirSip() {
        System.out.print("Introduce tu SIP: ");
        return scanner.nextLine();
    }

    public int mostrarMenuPaciente() {
        System.out.println("Menú Paciente");
        System.out.println("1. Ver citas");
        System.out.println("2. Ver historial");
        System.out.println("3. Solicitar nueva cita");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String pedirMotivoCita() {
        System.out.print("Motivo de la cita: ");
        return scanner.nextLine();
    }

    public String pedirFecha() {
        System.out.print("Fecha (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

    public String pedirHora() {
        System.out.print("Hora (HH:MM): ");
        return scanner.nextLine();
    }

    public void mostrarCitas(List<Cita> citas) {
        if (citas.isEmpty()) {
            System.out.println("No tienes citas programadas.");
        } else {
            System.out.println("Tus citas:");
            for (Cita cita : citas) {
                System.out.println(cita);
            }
        }
    }

    public void mostrarHistorial(Paciente paciente) {
        System.out.println("Historial médico de: " + paciente.getNombre());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
