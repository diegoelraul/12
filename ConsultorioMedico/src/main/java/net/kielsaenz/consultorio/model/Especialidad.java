package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Hace referencia a una especialidad perteneciente a un medico
 * @author dew - Grupo 04
 */
@Entity
@Table(name = "ESPECIALIDAD")
public class Especialidad extends Bean {

    @ManyToOne(targetEntity = Empresa.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private Empresa empresa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ESPECIALIDAD_ID", nullable = false)
    private Integer especialidadId;
    @Column(name = "NOMBRE", nullable = false, length = 25)
    private String nombre;
    @Column(name = "ACTIVO", nullable = false, length = 1)
    private String activo;

    public Especialidad() {
        super();
    }

    public Especialidad(Empresa empresa, Integer especialidadId, String nombre, String activo) {
        this.empresa = empresa;
        this.especialidadId = especialidadId;
        this.nombre = nombre;
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

    /**
     * @return the especialidadId
     */
    public Integer getEspecialidadId() {
        return especialidadId;
    }

    /**
     * @param especialidadId the especialidadId to set
     */
    public void setEspecialidadId(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("especialidadId = ").append(especialidadId).append(", ");
        strBuffer.append("empresa = ").append(empresa.toString()).append(", ");
        strBuffer.append("nombre = ").append(nombre).append(", ");
        strBuffer.append("activo = ").append(activo).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.nombre = nombre.toUpperCase();
        this.activo = activo.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.nombre = nombre.toLowerCase();
        this.activo = activo.toLowerCase();
    }
}
