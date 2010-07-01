package net.kielsaenz.consultorio.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hace referencia a una persona, de la cual heredan los datos medico y paciente
 * @author dew - Grupo 04
 */
@Entity
@Table(name = "PERSONA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING)
public class Persona extends Bean {

    public static String PACIENTE = "P";
    public static String MEDICO = "M";
    @ManyToOne(targetEntity = Empresa.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private Empresa empresa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSONA_ID", nullable = false)
    private Integer personaId;
    @Column(name = "NOMBRES", nullable = false, length = 25)
    private String nombres;
    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 20)
    private String apellidoPaterno;
    @Column(name = "APELLIDO_MATERNO", nullable = false, length = 20)
    private String apellidoMaterno;
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "NRO_DOCUMENTO", nullable = true, length = 8)
    private String nroDocumento;
    @Column(name = "RUTA_FOTO", nullable = true, length = 25)
    private String rutaFoto;
    @Column(name = "ACTIVO", nullable = false, length = 1)
    private String activo;
    @Column(name = "DIRECCION", nullable = false, length = 40)
    private String direccion;
    @Column(name = "URBANIZACION", nullable = true, length = 25)
    private String urbanizacion;
    @ManyToOne(targetEntity = DepProvDist.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "DEPARTAMENTO_ID", referencedColumnName = "DEPARTAMENTO_ID"),
        @JoinColumn(name = "PROVINCIA_ID", referencedColumnName = "PROVINCIA_ID"),
        @JoinColumn(name = "DISTRITO_ID", referencedColumnName = "DISTRITO_ID")
    })
    private DepProvDist depProvDist;
    @Column(name = "TELEFONO_FIJO", nullable = true, length = 20)
    private String telefonoFijo;
    @Column(name = "TELEFONO_CELULAR", nullable = true, length = 20)
    private String telefonoCelular;
    @Column(name = "EMAIL", nullable = true, length = 45)
    private String eMail;

    public Persona() {
        super();
    }

    public Persona(Empresa empresa, Integer personaId, String nombres,
            String apellidoPaterno, String apellidoMaterno,
            Date fechaNacimiento, String nroDocumento, String rutaFoto,
            String activo, String direccion, String urbanizacion,
            DepProvDist depProvDist, String telefonoFijo,
            String telefonoCelular, String eMail) {
        this.empresa = empresa;
        this.personaId = personaId;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.nroDocumento = nroDocumento;
        this.rutaFoto = rutaFoto;
        this.activo = activo;
        this.direccion = direccion;
        this.urbanizacion = urbanizacion;
        this.depProvDist = depProvDist;
        this.telefonoFijo = telefonoFijo;
        this.telefonoCelular = telefonoCelular;
        this.eMail = eMail;
    }

    /**
     * @return the apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * @param apellidoPaterno the apellidoPaterno to set
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * @return the apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * @param apellidoMaterno the apellidoMaterno to set
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return fecha nacimiento formateada
     */
    public String getFechaNacimientoFormat(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(fechaNacimiento);
    }

    /**
     * @return edad
     */
    public int getEdad() {

        Calendar cal1 = new GregorianCalendar();
        cal1.setTimeInMillis(this.fechaNacimiento.getTime());
        Calendar cal2 = new GregorianCalendar();
        cal2.setTimeInMillis(new Date().getTime());

        return cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
    }

    /**
     * @return the nroDocumento
     */
    public String getNroDocumento() {
        return nroDocumento;
    }

    /**
     * @param nroDocumento the nroDocumento to set
     */
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    /**
     * @return el nombre completo;
     */
    public String getNombreCompleto() {
        return getApellidoPaterno() + " " + getApellidoMaterno() + ", " + getNombres();
    }

    /**
     * @return the personaId
     */
    public Integer getPersonaId() {
        return personaId;
    }

    /**
     * @param personaId the personaId to set
     */
    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public DepProvDist getDepProvDist() {
        return depProvDist;
    }

    public void setDepProvDist(DepProvDist depProvDist) {
        this.depProvDist = depProvDist;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void toUpperCase() {
        this.nombres = nombres.toUpperCase();
        this.apellidoPaterno = apellidoPaterno.toUpperCase();
        this.apellidoMaterno = apellidoMaterno.toUpperCase();
        this.nroDocumento = nroDocumento.toUpperCase();
        this.rutaFoto = rutaFoto.toUpperCase();
        this.activo = activo.toUpperCase();
        this.direccion = direccion.toUpperCase();
        this.urbanizacion = urbanizacion.toUpperCase();
        this.telefonoFijo = telefonoFijo.toUpperCase();
        this.telefonoCelular = telefonoCelular.toUpperCase();
        this.eMail = eMail.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.nombres = nombres.toLowerCase();
        this.apellidoPaterno = apellidoPaterno.toLowerCase();
        this.apellidoMaterno = apellidoMaterno.toLowerCase();
        this.nroDocumento = nroDocumento.toLowerCase();
        this.rutaFoto = rutaFoto.toLowerCase();
        this.activo = activo.toLowerCase();
        this.direccion = direccion.toLowerCase();
        this.urbanizacion = urbanizacion.toLowerCase();
        this.telefonoFijo = telefonoFijo.toLowerCase();
        this.telefonoCelular = telefonoCelular.toLowerCase();
        this.eMail = eMail.toLowerCase();
    }
}
