package app;

import controller.ControllerDoctor;
import controller.ControllerPaciente;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws SQLException {
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("Menú Hospital");
                System.out.println("1. Entrar como Doctor");//12345678A
                System.out.println("2. Entrar como Paciente");//100000001
                System.out.println("3. Salir");
                System.out.print("Elige una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        new ControllerDoctor().iniciarSesionDoctor();
                        break;
                    case 2:
                        new ControllerPaciente().iniciarSesionPaciente();
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 3);
        }
    }