package model;
/**
 * Clase que representa un tratamiento médico.
 * Contiene información como el nombre y la descripción del tratamiento.
 * Corresponde a la tabla Tratamiento en la base de datos.
 * @author Sergio Zambrana
 */

public class Tratamiento {
    private int idTratamiento;
    private String nombre;
    private String descripcion;

    /**
     * Constructor con todos los campos.
     * @param idTratamiento ID del tratamiento.
     * @param nombre Nombre del tratamiento.
     * @param descripcion Descripción del tratamiento.
     */
    public Tratamiento(int idTratamiento, String nombre, String descripcion) {
        this.idTratamiento = idTratamiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
     * Obtiene el nombre del tratamiento.
     * @return Nombre del tratamiento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del tratamiento.
     * @param nombre Nuevo nombre del tratamiento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del tratamiento.
     * @return Descripción del tratamiento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del tratamiento.
     * @param descripcion Nueva descripción del tratamiento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

