package model;

/**
 * Clase Doctor. Representa a un médico en el sistema de gestión hospitalaria
 * Contiene información como DNI, nombre, email, especialidad y teléfono del doctor.
 *
 * @author Liam Rodriguez
 * @version 01-2025
 * @since 2025
 */
public class Doctor {
    private String dni;// Documento Nacional de Identidad del doctor
    private String nombre;// Nombre completo del doctor
    private String email;// Correo electrónico del doctor
    private String especialidad;// Especialidad médica del doctor
    private String telefono;// Teléfono de contacto del doctor

    /**
     * Constructor con todos los parámetros.
     *
     * @param dni DNI del doctor
     * @param nombre Nombre del doctor
     * @param email Correo electrónico del doctor
     * @param especialidad Especialidad médica
     * @param telefono Teléfono de contacto
     */
    public Doctor(String dni, String nombre, String email, String especialidad, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    /**
     * Obtiene el DNI del doctor.
     * @return DNI
     */
    public String getDni() {
        return dni;
    }

    /**
     * Asigna el DNI del doctor.
     * @param dni Nuevo DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del doctor.
     * @return Nombre completo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del doctor.
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del doctor.
     * @return Correo electrónico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna el email del doctor.
     * @param email Nuevo correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la especialidad del doctor.
     * @return Especialidad médica
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Asigna la especialidad del doctor.
     * @param especialidad Nueva especialidad médica
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el teléfono del doctor.
     * @return Teléfono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el teléfono del doctor.
     * @param telefono Nuevo número de teléfono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}