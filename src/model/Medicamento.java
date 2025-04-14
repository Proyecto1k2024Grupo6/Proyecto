package model;

/**
 * Clase Medicamento. Representa un medicamento con su información básica
 * @author Todos
 * @version 01-2025
 * @since 2025
 */
public class Medicamento {
    private String id;
    private String nombre;
    private String descripcion;

    /**
     * Constructor con todos los parámetros.
     * @param id Identificador del medicamento
     * @param nombre Nombre del medicamento
     * @param descripcion Descripción del medicamento
     */
    public Medicamento(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Medicamento() {}

    /**
     * Obtiene el ID del medicamento.
     * @return ID del medicamento
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del medicamento.
     * @param id Nuevo ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del medicamento.
     * @return Nombre del medicamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del medicamento.
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del medicamento.
     * @return Descripción del medicamento
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del medicamento.
     * @param descripcion Nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
