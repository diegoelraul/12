package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UbigeoPK extends Bean {

    @Column(name = "DEPARTAMENTO_ID", length=2)
    private String departamentoId;
    @Column(name = "PROVINCIA_ID", length=2)
    private String provinciaId;
    @Column(name = "DISTRITO_ID", length=2)
    private String distritoId;

    public UbigeoPK() {
        super();
    }

    public UbigeoPK(String departamentoId, String provinciaId, String distritoId) {
        this.departamentoId = departamentoId;
        this.provinciaId = provinciaId;
        this.distritoId = distritoId;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(String provinciaId) {
        this.provinciaId = provinciaId;
    }

    public String getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(String distritoId) {
        this.distritoId = distritoId;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("departamentoId = ").append(departamentoId).append(", ");
        strBuffer.append("provinciaId = ").append(provinciaId).append(", ");
        strBuffer.append("distritoId = ").append(distritoId).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {}

    @Override
    public void toLowerCase() {}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UbigeoPK other = (UbigeoPK) obj;
        if ((this.departamentoId == null) ? (other.departamentoId != null) : !this.departamentoId.equals(other.departamentoId)) {
            return false;
        }
        if ((this.provinciaId == null) ? (other.provinciaId != null) : !this.provinciaId.equals(other.provinciaId)) {
            return false;
        }
        if ((this.distritoId == null) ? (other.distritoId != null) : !this.distritoId.equals(other.distritoId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.departamentoId != null ? this.departamentoId.hashCode() : 0);
        hash = 37 * hash + (this.provinciaId != null ? this.provinciaId.hashCode() : 0);
        hash = 37 * hash + (this.distritoId != null ? this.distritoId.hashCode() : 0);
        return hash;
    }
}
