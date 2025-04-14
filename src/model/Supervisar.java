package model;

/**
 * Clase que representa la relación entre un doctor y un tratamiento supervisado.
 * Incluye observaciones del doctor sobre el tratamiento.
 * Corresponde a la tabla Supervisar en la base de datos.
 * Utiliza claves foráneas hacia Doctor y Tratamiento.
 * @author Sergio Zambrana
 */
public class Supervisar {
    private int idDoctor;
    private int idTratamiento;
    private String observaciones;

    /**
     * Constructor con todos los campos.
     * @param idDoctor ID del doctor.
     * @param idTratamiento ID del tratamiento.
     * @param observaciones Observaciones del doctor.
     */
    public Supervisar(int idDoctor, int idTratamiento, String observaciones) {
        this.idDoctor = idDoctor;
        this.idTratamiento = idTratamiento;
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el ID del doctor.
     * @return ID del doctor.
     */
    public int getIdDoctor() {
        return idDoctor;
    }

    /**
     * Establece el ID del doctor.
     * @param idDoctor Nuevo ID del doctor.
     */
    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * Obtiene el ID del tratamiento.
     * @return ID del tratamiento.
     */
    public int getIdTratamiento() {
        return idTratamiento;
    }

    /**
     * Establece el ID del tratamiento.
     * @param idTratamiento Nuevo ID del tratamiento.
     */
    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    /**
     * Obtiene las observaciones del doctor.
     * @return Observaciones del tratamiento.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones del doctor.
     * @param observaciones Nuevas observaciones.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
