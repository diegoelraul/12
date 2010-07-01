package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Kiel
 */
@Embeddable
public class MedicoEspecialidadSucursalPK extends Bean{

    @Column(name = "SUCURSAL_ID")
    private Integer sucursalId;
    @Column(name = "ESPECIALIDAD_ID")
    private Integer especialidadId;
    @Column(name = "MEDICO_ID")
    private Integer medicoId;

    public MedicoEspecialidadSucursalPK() {
        super();
    }

    public MedicoEspecialidadSucursalPK(Integer sucursalId, Integer especialidadId, Integer medicoId) {
        this.sucursalId = sucursalId;
        this.especialidadId = especialidadId;
        this.medicoId = medicoId;
    }

    public Integer getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Integer sucursalId) {
        this.sucursalId = sucursalId;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("sucursalId = ").append(sucursalId).append(", ");
        strBuffer.append("especialidadId = ").append(especialidadId).append(", ");
        strBuffer.append("medicoId = ").append(medicoId).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {}

    @Override
    public void toLowerCase() {}
}
