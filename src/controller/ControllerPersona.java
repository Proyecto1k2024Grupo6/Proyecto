/*
package controller;

private PersonaDAO personaDAO;
private VistaPersona vistaPersona;

public ControllerPersona() {
    // Crear conexión a la base de datos

    personaDAO = PersonaDAO.getInstance();
    vistaPersona = new VistaPersona();
}

public void mostrarTodasLasPersonas() {
    try {
        List<Persona> personas = personaDAO.getAllPersonas();
        vistaPersona.mostrarPersonas(personas);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void mostraPersonaDNI() {
    try {
        String dni = vistaPersona.obtenerDni();
        Persona persona = personaDAO.getPersonaByDni(dni);
        vistaPersona.mostrarPersona(persona);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void crearPersona() {
    try {
        Persona persona=vistaPersona.crearPersona();
        personaDAO.insertPersona(persona);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void actualizarPersona() {
    try {
        Persona persona=vistaPersona.obtenerDatosActualizados();
        personaDAO.updatePersona(persona);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void eliminarPersona() {
    try {
        String dni=vistaPersona.obtenerDniAEliminar();
        personaDAO.deletePersonaByDni(dni);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Otros métodos del controlador...
}*/
