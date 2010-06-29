package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UbigeoPK extends Bean {

    @Column(name = "DEPARTAMENTO_ID")
    private String departamentoId;
    @Column(name = "PROVINCIA_ID")
    private String provinciaId;
    @Column(name = "DISTRITO_ID")
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
}
