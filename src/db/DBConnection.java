package db;

import java.sql.*;

public class DBConnection {
    // URL de conexión a la base de datos incluye host, puerto, y nombre de la BD
    private static final String URL = "jdbc:mysql://databaseproyecto.cakrctirtenk.us-east-1.rds.amazonaws.com:3306/grupo6";
    // Credenciales de acceso a la base de datos
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin1234";
    // Instancia única de la conexión
    private static Connection connection;
    /**
     * Constructor privado para evitar instanciación directa.
     */
    private DBConnection() {}
    /**
     * Establece y devuelve la conexión a la base de datos.
     * Si ya existe una conexión, la devuelve sin crear una nueva.
     *
     * @return Objeto Connection para interactuar con la base de datos.
     */
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DBConnection.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                        System.out.println("Conexión establecida con la base de datos.");
                    } catch (SQLException e) {
                        System.err.println(" Error al conectar a la base de datos:");
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
    /**
     * Cierra la conexión con la base de datos si está activa.
     * Es importante liberar los recursos una vez que se deja de usar.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println(" Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println(" Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }
    /*// 👇 Este es tu método de prueba
    public static void main(String[] args) {
        Connection conn = getConnection();

        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM DOCTOR LIMIT 5");
                System.out.println("Lista de doctores:");
                while (rs.next()) {
                    String dni = rs.getString("dni");
                    String nombre = rs.getString("nombre");
                    String especialidad = rs.getString("especialidad");
                    System.out.println(" " + nombre + " | DNI: " + dni + " | Especialidad: " + especialidad);
                }
            } catch (SQLException e) {
                System.err.println(" Error al ejecutar la consulta:");
                e.printStackTrace();
            } finally {
                closeConnection();
            }*/
       //}
    //}
}