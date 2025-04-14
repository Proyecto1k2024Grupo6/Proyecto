package model;

/**
 * Clase Hospitalizar, representa la relación entre un doctor y una hospitalización asignada
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class Hospitalizar {
    private int idHospitalizacion; // ID de la hospitalización
    private String dniDoctor; // DNI del doctor asignado

    /**
     * Constructor con todos los parámetros.
     * @param idHospitalizacion ID de la hospitalización
     * @param dniDoctor DNI del doctor asignado
     */
    public Hospitalizar(int idHospitalizacion, String dniDoctor) {
        this.idHospitalizacion = idHospitalizacion;
        this.dniDoctor = dniDoctor;
    }

    /**
     * @return ID de la hospitalización
     */
    public int getIdHospitalizacion() {
        return idHospitalizacion;
    }

    /**
     * @param idHospitalizacion Nuevo ID a asignar
     */
    public void setIdHospitalizacion(int idHospitalizacion) {
        this.idHospitalizacion = idHospitalizacion;
    }

    /**
     * @return DNI del doctor asignado
     */
    public String getDniDoctor() {
        return dniDoctor;
    }

    /**
     * @param dniDoctor DNI del nuevo doctor a asignar
     */
    public void setDniDoctor(String dniDoctor) {
        this.dniDoctor = dniDoctor;
    }
}