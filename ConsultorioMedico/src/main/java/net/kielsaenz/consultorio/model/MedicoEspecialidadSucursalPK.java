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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedicoEspecialidadSucursalPK other = (MedicoEspecialidadSucursalPK) obj;
        if (this.sucursalId != other.sucursalId && (this.sucursalId == null || !this.sucursalId.equals(other.sucursalId))) {
            return false;
        }
        if (this.especialidadId != other.especialidadId && (this.especialidadId == null || !this.especialidadId.equals(other.especialidadId))) {
            return false;
        }
        if (this.medicoId != other.medicoId && (this.medicoId == null || !this.medicoId.equals(other.medicoId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.sucursalId != null ? this.sucursalId.hashCode() : 0);
        hash = 47 * hash + (this.especialidadId != null ? this.especialidadId.hashCode() : 0);
        hash = 47 * hash + (this.medicoId != null ? this.medicoId.hashCode() : 0);
        return hash;
    }
}
