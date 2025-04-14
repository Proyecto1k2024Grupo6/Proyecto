package model;

/**
 * Clase que representa la relación entre un paciente y un tratamiento recibido.
 * Contiene las fechas de inicio y fin del tratamiento.
 * Corresponde a la tabla Recibir en la base de datos.
 * Utiliza claves foráneas hacia Paciente y Tratamiento.
 * @author Sergio Zambrana
 */
import java.time.LocalDate;

public class Recibir {
    private int idPaciente;
    private int idTratamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    /**
     * Constructor con todos los campos.
     * @param idPaciente ID del paciente.
     * @param idTratamiento ID del tratamiento.
     * @param fechaInicio Fecha de inicio del tratamiento.
     * @param fechaFin Fecha de finalización del tratamiento.
     */
    public Recibir(int idPaciente, int idTratamiento, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idPaciente = idPaciente;
        this.idTratamiento = idTratamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene el ID del paciente.
     * @return ID del paciente.
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * Establece el ID del paciente.
     * @param idPaciente Nuevo ID del paciente.
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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
     * Obtiene la fecha de inicio del tratamiento.
     * @return Fecha de inicio.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio del tratamiento.
     * @param fechaInicio Nueva fecha de inicio.
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha de fin del tratamiento.
     * @return Fecha de finalización.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin del tratamiento.
     * @param fechaFin Nueva fecha de fin.
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
