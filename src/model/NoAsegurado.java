package model;
/**
 * Clase NoAsegurado. Representa a un paciente no asegurado en el sistema de gestión hospitalaria.
 * Contiene información como sip;
 *
 * @author Alejandro Rodriguez Blazquez
 * @version 01-2025
 * @since 2025
 */

public class NoAsegurado {
    private int sip;

    public NoAsegurado(int sip) {
        this.sip = sip;
    }

    public int getSip() {
        return sip;
    }

    public void setSip(int sip) {
        this.sip = sip;
    }
}
