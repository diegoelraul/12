package net.kielsaenz.consultorio.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Hace referencia a un medico que brinda atencion en la clinica
 * @author dew - Grupo 04
 */
@Entity
@DiscriminatorValue(value = "M")
public class Medico extends Persona {

    @Column(name = "NRO_COLEGIATURA", nullable = false, length = 5)
    private String nroColegiatura;

    public Medico() {
        super();
    }

    public Medico(Empresa empresa, Integer personaId, String nombres,
            String apellidoPaterno, String apellidoMaterno,
            Date fechaNacimiento, String nroDocumento, String rutaFoto,
            String activo, String direccion, String urbanizacion,
            DepProvDist depProvDist, String telefonoFijo,
            String telefonoCelular, String eMail, String nroColegiatura) {
        super(empresa, personaId, nombres, apellidoPaterno, apellidoMaterno,
                fechaNacimiento, nroDocumento, rutaFoto, activo, direccion,
                urbanizacion, depProvDist, telefonoFijo, telefonoCelular, eMail);
        this.nroColegiatura = nroColegiatura;
    }

    /**
     * @return the nroColegiatura
     */
    public String getNroColegiatura() {
        return nroColegiatura;
    }

    /**
     * @param nroColegiatura the nroColegiatura to set
     */
    public void setNroColegiatura(String nroColegiatura) {
        this.nroColegiatura = nroColegiatura;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("persona = ").append(super.toString()).append(", ");
        strBuffer.append("nroColegiatura = ").append(nroColegiatura).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.nroColegiatura = nroColegiatura.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.nroColegiatura = nroColegiatura.toLowerCase();
    }
}
