package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Kiel
 */
@Entity
@Table(name = "DEP_PROV_DIST")
public class DepProvDist extends Bean {

    @EmbeddedId
    private UbigeoPK ubigeoPK;
    @Column(name = "DEPARTAMENTO", length = 50, nullable = false)
    private String departamento;
    @Column(name = "PROVINCIA", length = 50, nullable = false)
    private String provincia;
    @Column(name = "DISTRITO", length = 50, nullable = false)
    private String distrito;

    public DepProvDist() {
        super();
    }

    public DepProvDist(UbigeoPK ubigeoPK, String departamento, String provincia, String distrito) {
        this.departamento = departamento;
        this.ubigeoPK = ubigeoPK;
        this.provincia = provincia;
        this.distrito = distrito;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public UbigeoPK getUbigeoPK() {
        return ubigeoPK;
    }

    public void setUbigeoPK(UbigeoPK ubigeoPK) {
        this.ubigeoPK = ubigeoPK;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("ubigeoPK = ").append(ubigeoPK.toString()).append(", ");
        strBuffer.append("departamento = ").append(departamento).append(", ");
        strBuffer.append("provincia = ").append(provincia).append(", ");
        strBuffer.append("distrito = ").append(distrito).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.departamento = departamento.toUpperCase();
        this.provincia = provincia.toUpperCase();
        this.distrito = distrito.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.departamento = departamento.toLowerCase();
        this.provincia = provincia.toLowerCase();
        this.distrito = distrito.toLowerCase();
    }
}
