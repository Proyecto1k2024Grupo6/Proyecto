package model;
/**
 * Clase Paciente. Representa a un paciente en el sistema de gestión hospitalaria.
 * Contiene información como SIP, nombre, fecha de nacimiento, teléfono, DNI y el DNI de su doctor asignado.
 *
 * @author Alejandro Rodriguez Blazquez
 * @version 01-2025
 * @since 2025
 */
public class Paciente {
    private int sip; // Código SIP del paciente
    private String nombre; // Nombre completo del paciente
    private String fnac; // Fecha de nacimiento del paciente (formato: YYYY-MM-DD)
    private String telefono; // Teléfono de contacto del paciente
    private String dni; // Documento Nacional de Identidad del paciente
    private String dniDoctor; // DNI del doctor asignado al paciente

    /**
     * Constructor con todos los parámetros.
     *
     * @param sip Código SIP del paciente
     * @param nombre Nombre del paciente
     * @param fnac Fecha de nacimiento (YYYY-MM-DD)
     * @param telefono Teléfono de contacto
     * @param dni DNI del paciente
     * @param dniDoctor DNI del doctor asignado
     */
    public Paciente(int sip, String nombre, String fnac, String telefono, String dni, String dniDoctor) {
        this.sip = sip;
        this.nombre = nombre;
        this.fnac = fnac;
        this.telefono = telefono;
        this.dni = dni;
        this.dniDoctor = dniDoctor;
    }

    public int getSip() {
        return sip;
    }

    public void setSip(int sip) {
        this.sip = sip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDniDoctor() {
        return dniDoctor;
    }

    public void setDniDoctor(String dniDoctor) {
        this.dniDoctor = dniDoctor;
    }
}

