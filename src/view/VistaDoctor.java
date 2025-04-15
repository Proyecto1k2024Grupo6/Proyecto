package view;

import model.Cita;
import model.Doctor;

import java.util.List;
import java.util.Scanner;

public class VistaDoctor {
    private Scanner scanner = new Scanner(System.in);

    // Función para pedir el DNI del doctor
    public String pedirDni() {
        System.out.print("Introduce tu DNI (Doctor): ");
        return scanner.nextLine();
    }

    // Función para mostrar el mensaje de bienvenida del doctor
    public void mostrarBienvenida(Doctor doctor) {
        System.out.println("Bienvenido  " + doctor.getNombre() + ", Teléfono: " + doctor.getTelefono() + ", Especialidad: " + doctor.getEspecialidad());
    }

    // Función para mostrar las citas
    public void mostrarCitas(List<Cita> citas) {
        if (citas.isEmpty()) {
            System.out.println("No tienes citas programadas para hoy.");
        } else {
            System.out.println("Citas para hoy:");
            for (Cita cita : citas) {
                System.out.println("- " + cita);
            }
        }
    }

    // Función para mostrar mensajes
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Función para mostrar el menú del doctor
    public int mostrarMenuDoctor() {
        System.out.println("Menú Doctor");
        System.out.println("1. Ver citas de hoy");
        System.out.println("2. Salir");
        System.out.print("Elige una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
