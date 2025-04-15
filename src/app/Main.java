package app;

public class Main {
    public static void main(String[] args) {
        //VistaPersona vista = new VistaPersona();
        ControllerPersona controlador = new ControllerPersona();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Mostrar todas las personas");
            System.out.println("2. Crear persona");
            System.out.println("3. Actualizar persona");
            System.out.println("4. Eliminar persona");
            System.out.println("5. Mostrar persona por DNI");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    controlador.mostrarTodasLasPersonas();
                    break;
                case 2:
                    controlador.crearPersona();
                    System.out.println("Persona creada correctamente.");
                    break;
                case 3:
                    controlador.actualizarPersona();
                    System.out.println("Persona actualizada correctamente.");
                    break;
                case 4:
                    controlador.eliminarPersona();
                    System.out.println("Persona eliminada correctamente.");
                    break;
                case 5:
                    controlador.mostraPersonaDNI();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }
}