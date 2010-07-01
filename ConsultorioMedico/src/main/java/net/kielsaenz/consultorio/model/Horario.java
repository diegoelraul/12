package net.kielsaenz.consultorio.model;

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hace referencia a un horario de atencion
 * @author dew - Grupo 04
 */
@Entity
@Table(name = "HORARIO")
public class Horario extends Bean {

    @ManyToOne(targetEntity = Empresa.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private Empresa empresa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HORARIO_ID", nullable = false)
    private Integer horarioId;
    @ManyToOne(targetEntity = Medico.class, optional = false)
    @JoinColumn(name = "MEDICO_ID")
    private Medico medico;
    @ManyToOne(targetEntity = Especialidad.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ESPECIALIDAD_ID")
    private Especialidad especialidad;
    @ManyToOne(targetEntity = Sucursal.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "SUCURSAL_ID")
    private Sucursal sucursal;
    @Column(name = "DIA_SEMANA", nullable = false)
    private Integer diaSemana;
    @Column(name = "HORA_INICIO", nullable = false)
    //@Temporal(value = TemporalType.TIME)
    private Time horaInicio;
    @Column(name = "HORA_FIN", nullable = false)
    //@Temporal(value = TemporalType.TIME)
    private Time horaFin;
    @Column(name = "ACTIVO", nullable = false, length = 1)
    private String activo;

    public Horario() {
        super();
    }

    public Horario(Empresa empresa, Integer horarioId, Medico medico,
            Especialidad especialidad, Sucursal sucursal, Integer diaSemana,
            Time horaInicio, Time horaFin, String activo) {
        this.empresa = empresa;
        this.horarioId = horarioId;
        this.medico = medico;
        this.especialidad = especialidad;
        this.sucursal = sucursal;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.activo = activo;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the horarioId
     */
    public Integer getHorarioId() {
        return horarioId;
    }

    /**
     * @param horarioId the horarioId to set
     */
    public void setHorarioId(Integer horarioId) {
        this.horarioId = horarioId;
    }

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the especialidad
     */
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("horarioId = ").append(horarioId).append(", ");
        strBuffer.append("empresa = ").append(empresa.toString()).append(", ");
        strBuffer.append("medico = ").append(medico).append(", ");
        strBuffer.append("especialidad = ").append(especialidad.toString()).append(", ");
        strBuffer.append("sucursal = ").append(sucursal.toString()).append(", ");
        strBuffer.append("diaSemana = ").append(diaSemana).append(", ");
        strBuffer.append("horaInicio = ").append(horaInicio.toString()).append(", ");
        strBuffer.append("horaFin = ").append(horaFin.toString()).append(", ");
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
