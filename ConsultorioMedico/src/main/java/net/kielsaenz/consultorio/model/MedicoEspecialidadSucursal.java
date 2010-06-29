/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Kiel
 */
@Entity
@Table(name = "MEDICO_ESPECIALIDAD_SUCURSAL")
public class MedicoEspecialidadSucursal extends Bean {

    @EmbeddedId
    private MedicoEspecialidadSucursalPK medicoEspecialidadSucursalPK;
    @ManyToOne(targetEntity = Empresa.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private Empresa empresa;
    @Column(name = "ACTIVO", nullable = false, length = 1)
    private String activo;

    public MedicoEspecialidadSucursal() {
        super();
    }

    public MedicoEspecialidadSucursal(MedicoEspecialidadSucursalPK medicoEspecialidadSucursalPK, Empresa empresa, String activo) {
        this.medicoEspecialidadSucursalPK = medicoEspecialidadSucursalPK;
        this.empresa = empresa;
        this.activo = activo;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public MedicoEspecialidadSucursalPK getMedicoEspecialidadSucursalPK() {
        return medicoEspecialidadSucursalPK;
    }

    public void setMedicoEspecialidadSucursalPK(MedicoEspecialidadSucursalPK medicoEspecialidadSucursalPK) {
        this.medicoEspecialidadSucursalPK = medicoEspecialidadSucursalPK;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("medicoEspecialidadSucursalPK = ").append(medicoEspecialidadSucursalPK.toString()).append(", ");
        strBuffer.append("empresa = ").append(empresa.toString()).append(", ");
        strBuffer.append("activo = ").append(activo).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.activo = activo.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.activo = activo.toLowerCase();
    }
}
