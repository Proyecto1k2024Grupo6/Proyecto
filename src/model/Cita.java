package model;

import java.time.LocalDateTime;

/**
 * Clase Cita. Representa una cita médica entre un paciente y un doctor.
 * Contiene información sobre la fecha y hora, motivo, paciente y doctor asignado.
 *
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class Cita {
    private int id; // Identificador único de la cita
    private LocalDateTime fechaHora; // Fecha y hora de la cita
    private String motivo; // Motivo de la consulta
    private String dniPaciente; // DNI del paciente
    private String dniDoctor; // DNI del doctor

    /**
     * Constructor con todos los parámetros.
     *
     * @param id          Identificador de la cita
     * @param fechaHora   Fecha y hora de la cita
     * @param motivo      Motivo de la cita
     * @param dniPaciente DNI del paciente que asiste a la cita
     * @param dniDoctor   DNI del doctor que atenderá la cita
     */
    public Cita(int id, LocalDateTime fechaHora, String motivo, String dniPaciente, String dniDoctor) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.dniPaciente = dniPaciente;
        this.dniDoctor = dniDoctor;
    }

    /**
     * Obtiene el ID de la cita.
     *
     * @return ID de la cita
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el ID de la cita.
     *
     * @param id Nuevo ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora de la cita.
     *
     * @return Fecha y hora como LocalDateTime
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Asigna la fecha y hora de la cita.
     *
     * @param fechaHora Nueva fecha y hora
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el motivo de la cita.
     *
     * @return Motivo de la consulta
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Asigna el motivo de la cita.
     *
     * @param motivo Nuevo motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene el DNI del paciente.
     *
     * @return DNI del paciente
     */
    public String getDniPaciente() {
        return dniPaciente;
    }

    /**
     * Asigna el DNI del paciente.
     *
     * @param dniPaciente Nuevo DNI del paciente
     */
    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    /**
     * Obtiene el DNI del doctor.
     *
     * @return DNI del doctor
     */
    public String getDniDoctor() {
        return dniDoctor;
    }

    /**
     * Asigna el DNI del doctor.
     *
     * @param dniDoctor Nuevo DNI del doctor
     */
    public void setDniDoctor(String dniDoctor) {
        this.dniDoctor = dniDoctor;
    }

    /**
     * Sobrescribe el método toString para representar las citas de manera legible.
     *
     * @return Representación en String de la cita
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Fecha: " + fechaHora.toLocalDate() + ", Hora: " + fechaHora.toLocalTime() + ", Motivo: " + motivo + ", Paciente SIP: " + dniPaciente;
    }
}
