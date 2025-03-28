package db;

import java.sql.Connection;

/**
 * Clase PersonaDAO que gestiona el acceso a la base de datos para la entidad Persona.
 * Implementa el patrón Singleton para asegurar una única instancia.
 */
public class PersonaDAO {

    // Instancia única de PersonaDAO
    private static PersonaDAO instance;
    // Conexión a la base de datos
    private Connection connection;

    //Sentencia SQL para crear la si no existe
    public static final String CREATE_TABLE_PERSONA = """
    CREATE TABLE IF NOT EXISTS `Persona`(
    `id` int not null auto_increment,
    `dni` VARCHAR(9) NOT NULL,
    `nombre` VARCHAR(50) NOT NULL,
    `apellido` VARCHAR(50) NOT NULL,
    `edad` int NOT NULL,
                    primary key(`id`),
                    unique key(`dni`)    
);
""";
    // Consultas SQL predefinidas para operaciones CRUD
    private static final String INSERT_QUERY = "INSERT INTO Persona (dni, nombre, apellido, edad) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Persona";
    private static final String SELECT_BY_DNI_QUERY = "SELECT * FROM Persona WHERE dni = ?";
    private static final String UPDATE_QUERY = "UPDATE Persona SET nombre = ?, apellido = ?, edad = ? WHERE dni = ?";
    private static final String DELETE_QUERY = "DELETE FROM Persona WHERE dni = ?";
    private static final String TOTAL_PERSONAS_QUERY = "SELECT COUNT(*) FROM Persona";

    /**
     * Constructor privado para evitar instanciación externa.
     * Obtiene la conexión a la base de datos desde DBConnection.
     */
    private PersonaDAO() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Método estático para obtener la única instancia de PersonaDAO.
     * @return instancia única de PersonaDAO.
     */
    public static synchronized PersonaDAO getInstance() {
        if (instance == null) {
            instance = new PersonaDAO();
        }
        return instance;
    }

    /**
     * Inserta una nueva persona en la base de datos.
     * @param persona Objeto Persona a insertar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void insertPersona(Persona persona) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, persona.getDni());
            statement.setString(2, persona.getNombre());
            statement.setString(3, persona.getApellido());
            statement.setInt(4, persona.getEdad());
            statement.executeUpdate();
        }
    }

    /**
     * Obtiene todas las personas almacenadas en la base de datos.
     * @return Lista de objetos Persona.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public List<Persona> getAllPersonas() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                personas.add(resultSetToPersona(resultSet));
            }
        }
        return personas;
    }

    /**
     * Obtiene una persona a partir de su DNI.
     * @param dni Identificador único de la persona.
     * @return Objeto Persona si se encuentra, null si no.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Persona getPersonaByDni(String dni) throws SQLException {
        Persona persona = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_DNI_QUERY)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                persona = resultSetToPersona(resultSet);
            }
        }
        return persona;
    }

    /**
     * Actualiza los datos de una persona en la base de datos.
     * @param persona Objeto Persona con los datos actualizados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void updatePersona(Persona persona) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setInt(3, persona.getEdad());
            statement.setString(4, persona.getDni());
            statement.executeUpdate();
        }
    }

    /**
     * Elimina una persona de la base de datos por su DNI.
     * @param dni Identificador único de la persona a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void deletePersonaByDni(String dni) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }

    /**
     * Convierte un ResultSet en un objeto Persona.
     * @param resultSet Resultado de la consulta SQL.
     * @return Objeto Persona con los datos del ResultSet.
     * @throws SQLException Si ocurre un error en la conversión.
     */
    private Persona resultSetToPersona(ResultSet resultSet) throws SQLException {
        return new Persona(
                resultSet.getInt("id"),
                resultSet.getString("dni"),
                resultSet.getString("nombre"),
                resultSet.getString("apellido"),
                resultSet.getInt("edad"));
    }

    /**
     * Obtiene el total de personas almacenadas en la base de datos.
     * @return Número total de personas.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public int totalPersonas() throws SQLException {
        int total = 0;
        try (PreparedStatement statement = connection.prepareStatement(TOTAL_PERSONAS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }
        }
        return total;
    }
}
