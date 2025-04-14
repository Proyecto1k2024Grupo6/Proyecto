package model;

import java.time.LocalDate;

/**
 * Clase Hospitalizacion, representa una estancia hospitalaria de un paciente con sus fechas clave.
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class Hospitalizacion {
    private int idHospitalizacion; // Identificador de la hospitalización
    private String dniPaciente; // DNI del paciente hospitalizado
    private LocalDate fechaIngreso; // Fecha de ingreso al hospital
    private LocalDate fechaAlta; // Fecha de alta del hospital

    /**
     * Constructor con todos los parámetros.
     * @param idHospitalizacion ID único de la hospitalización
     * @param dniPaciente DNI del paciente
     * @param fechaIngreso Fecha de ingreso
     * @param fechaAlta Fecha de alta
     */
    public Hospitalizacion(int idHospitalizacion, String dniPaciente, LocalDate fechaIngreso, LocalDate fechaAlta) {
        this.idHospitalizacion = idHospitalizacion;
        this.dniPaciente = dniPaciente;
        this.fechaIngreso = fechaIngreso;
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return ID de la hospitalización
     */
    public int getIdHospitalizacion() {
        return idHospitalizacion;
    }

    /**
     * @param idHospitalizacion Nuevo ID de la hospitalización
     */
    public void setIdHospitalizacion(int idHospitalizacion) {
        this.idHospitalizacion = idHospitalizacion;
    }

    /**
     * @return DNI del paciente hospitalizado
     */
    public String getDniPaciente() {
        return dniPaciente;
    }

    /**
     * @param dniPaciente DNI del paciente que se desea registrar
     */
    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    /**
     * @return Fecha de ingreso al hospital
     */
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso Fecha en la que se desea registrar el ingreso
     */
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return Fecha de alta del hospital
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta Fecha en la que se dio el alta al paciente
     */
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}

