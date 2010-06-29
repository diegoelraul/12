package net.kielsaenz.consultorio.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hace referencia a una cita de atencion
 * @author dew - Grupo 04
 */
@Entity
@Table(name = "CITA")
public class Cita extends Bean {

    public static final String ESTADO_RESERVADO = "RESERVADO";
    public static final String ESTADO_PENDIENTE = "PENDIENTE";
    public static final String ESTADO_ATENDIDO = "ATENDIDO";
    @ManyToOne(targetEntity = Empresa.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private Empresa empresa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITA_ID", nullable = false)
    private Integer citaId;
    @Column(name = "ESTADO", nullable = false, length = 9)
    private String estado;
    @Column(name = "DIAGNOSTICO", nullable = true, length = 250)
    private String diagnostico;
    @ManyToOne(targetEntity = Paciente.class, optional = false)
    @JoinColumn(name = "PACIENTE_ID")
    private Paciente paciente;
    @OneToOne(targetEntity = Horario.class, optional = false)
    @JoinColumn(name = "HORARIO_ID")
    private Horario horario;
    @Column(name = "SINTOMA", nullable = true, length = 250)
    private String sintoma;
    @Column(name = "RECETA", nullable = true, length = 250)
    private String receta;
    @Column(name = "ANALISIS", nullable = true, length = 250)
    private String analisis;
    @Column(name = "OBSERVACIONES", nullable = true, length = 250)
    private String observaciones;
    @Column(name = "FECHA_PROXIMA_CITA", nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date fechaProximaCita;
    @Column(name = "FECHA_CITA", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date fechaCita;
    @Column(name = "PESO", nullable = true)
    private Double peso;
    @Column(name = "ESTATURA", nullable = true)
    private Double estatura;

    public Cita() {
        super();
    }

    public Cita(Empresa empresa, Integer citaId, String estado, String diagnostico, Paciente paciente, Horario horario, String sintoma, String receta, String analisis, String observaciones, Date fechaProximaCita, Date fechaCita, Double peso, Double estatura) {
        this.empresa = empresa;
        this.citaId = citaId;
        this.estado = estado;
        this.diagnostico = diagnostico;
        this.paciente = paciente;
        this.horario = horario;
        this.sintoma = sintoma;
        this.receta = receta;
        this.analisis = analisis;
        this.observaciones = observaciones;
        this.fechaProximaCita = fechaProximaCita;
        this.fechaCita = fechaCita;
        this.peso = peso;
        this.estatura = estatura;
    }

    /**
     * @return the citaId
     */
    public Integer getCitaId() {
        return citaId;
    }

    /**
     * @param citaId the citaId to set
     */
    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    /**
     * @return the sintoma
     */
    public String getSintoma() {
        return sintoma;
    }

    /**
     * @param sintoma the sintoma to set
     */
    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    /**
     * @return the receta
     */
    public String getReceta() {
        return receta;
    }

    /**
     * @param receta the receta to set
     */
    public void setReceta(String receta) {
        this.receta = receta;
    }

    /**
     * @return the analisis
     */
    public String getAnalisis() {
        return analisis;
    }

    /**
     * @param analisis the analisis to set
     */
    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the fechaProximaCita
     */
    public Date getFechaProximaCita() {
        return fechaProximaCita;
    }

    /**
     * @param fechaProximaCita the fechaProximaCita to set
     */
    public void setFechaProximaCita(Date fechaProximaCita) {
        this.fechaProximaCita = fechaProximaCita;
    }

    /**
     * @return el fecha proxima cita formateada
     */
    public String getFechaProximaCitaFormat(String format) {
        if (this.fechaProximaCita != null) {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(this.fechaProximaCita);
        } else {
            return "";
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Double getEstatura() {
        return estatura;
    }

    public void setEstatura(Double estatura) {
        this.estatura = estatura;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("empresa = ").append(empresa.toString()).append(", ");
        strBuffer.append("citaId = ").append(citaId).append(", ");
        strBuffer.append("horario = ").append(horario.toString()).append(", ");
        strBuffer.append("paciente = ").append(paciente.toString()).append(", ");
        strBuffer.append("fechaCita = ").append(fechaCita.toString()).append(", ");
        strBuffer.append("fechaProximaCita = ").append(fechaProximaCita.toString()).append(", ");
        strBuffer.append("sintoma = ").append(sintoma).append(", ");
        strBuffer.append("diagnostico = ").append(diagnostico).append(", ");
        strBuffer.append("observaciones = ").append(observaciones).append(", ");
        strBuffer.append("receta = ").append(receta).append(", ");
        strBuffer.append("analisis = ").append(analisis).append(", ");
        strBuffer.append("peso = ").append(peso).append(", ");
        strBuffer.append("estatura = ").append(estatura).append(", ");
        strBuffer.append("estado = ").append(estado).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.estado = estado.toUpperCase();
        this.diagnostico = diagnostico.toUpperCase();
        this.sintoma = sintoma.toUpperCase();
        this.receta = receta.toUpperCase();
        this.analisis = analisis.toUpperCase();
        this.observaciones = observaciones.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.estado = estado.toLowerCase();
        this.diagnostico = diagnostico.toLowerCase();
        this.sintoma = sintoma.toLowerCase();
        this.receta = receta.toLowerCase();
        this.analisis = analisis.toLowerCase();
        this.observaciones = observaciones.toLowerCase();
    }
}
