package net.kielsaenz.consultorio.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Hace referencia a un paciente que es atendido en la clinica
 * @author dew - Grupo 04
 */
@Entity
@DiscriminatorValue(value = "P")
public class Paciente extends Persona {

    @Column(name = "PESO", nullable = true)
    private Double peso;
    @Column(name = "ESTATURA", nullable = true)
    private Integer estatura;
    @Column(name = "GRUPO_SANGUINEO", nullable = true, length = 3)
    private String grupoSanguineo;

    public Paciente() {
        super();
    }

    public Paciente(Empresa empresa, Integer personaId, String nombres,
            String apellidoPaterno, String apellidoMaterno,
            Date fechaNacimiento, String nroDocumento, String rutaFoto,
            String activo, String direccion, String urbanizacion,
            DepProvDist depProvDist, String telefonoFijo,
            String telefonoCelular, String eMail, Double peso, Integer estatura,
            String grupoSanguineo) {
        super(empresa, personaId, nombres, apellidoPaterno, apellidoMaterno,
                fechaNacimiento, nroDocumento, rutaFoto, activo, direccion,
                urbanizacion, depProvDist, telefonoFijo, telefonoCelular, eMail);
        this.peso = peso;
        this.estatura = estatura;
        this.grupoSanguineo = grupoSanguineo;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the estatura
     */
    public Integer getEstatura() {
        return estatura;
    }

    /**
     * @param estatura the estatura to set
     */
    public void setEstatura(Integer estatura) {
        this.estatura = estatura;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("persona = ").append(super.toString()).append(", ");
        strBuffer.append("peso = ").append(peso).append(", ");
        strBuffer.append("estatura = ").append(estatura).append(", ");
        strBuffer.append("grupoSanguineo = ").append(grupoSanguineo).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.grupoSanguineo = grupoSanguineo.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.grupoSanguineo = grupoSanguineo.toLowerCase();
    }
}
