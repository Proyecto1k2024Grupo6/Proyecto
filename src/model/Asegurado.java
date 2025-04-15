package model;
/**
 * Clase Asegurado. Representa a un paciente asegurado en el sistema de gestión hospitalaria.
 * Contiene información como SIP y aseguradora.
 *
 * @author Alejandro Rodriguez Blazquez
 * @version 01-2025
 * @since 2025
 */
public class Asegurado {
    private int sip;
    private String aseguradora;

    public Asegurado(int sip, String aseguradora) {
        this.sip = sip;
        this.aseguradora = aseguradora;
    }

    public int getSip() {
        return sip;
    }

    public void setSip(int sip) {
        this.sip = sip;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }
}

