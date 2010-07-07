package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUCURSAL")
public class Sucursal extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUCURSAL_ID")
    private Integer sucursalId;
    @ManyToOne(targetEntity = Empresa.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_ID", nullable = false)
    private Empresa empresa;
    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;
    @Column(name = "DIRECCION", nullable = false, length = 40)
    private String direccion;
    @Column(name = "URBANIZACION", nullable = true, length = 25)
    private String urbanizacion;
    @Column(name = "TELEFONOS", nullable = true, length = 20)
    private String telefonos;
    @Column(name = "PRINCIPAL", nullable = false, length = 1)
    private String principal;
    @Column(name = "ACTIVO", nullable = false, length = 1)
    private String activo;
    @ManyToOne(targetEntity = DepProvDist.class, fetch=FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="DEPARTAMENTO_ID", referencedColumnName="DEPARTAMENTO_ID"),
        @JoinColumn(name="PROVINCIA_ID", referencedColumnName="PROVINCIA_ID"),
        @JoinColumn(name="DISTRITO_ID", referencedColumnName="DISTRITO_ID")
    })
    private DepProvDist depProvDist;

    public Sucursal() {
        super();
    }

    public Sucursal(Integer sucursalId, Empresa empresa, String nombre,
            String direccion, String urbanizacion, DepProvDist depProvDist,
            String telefonos, String principal, String activo) {
        this.sucursalId = sucursalId;
        this.empresa = empresa;
        this.nombre = nombre;
        this.direccion = direccion;
        this.urbanizacion = urbanizacion;
        this.telefonos = telefonos;
        this.principal = principal;
        this.activo = activo;
        this.depProvDist = depProvDist;
    }

    public Integer getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Integer sucursalId) {
        this.sucursalId = sucursalId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
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

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("sucursalId = ").append(sucursalId).append(", ");
        strBuffer.append("empresa = ").append(empresa.toString()).append(", ");
        strBuffer.append("nombre = ").append(nombre).append(", ");
        strBuffer.append("direccion = ").append(direccion).append(", ");
        strBuffer.append("urbanizacion = ").append(urbanizacion).append(", ");
        strBuffer.append("depProvDist = ").append(depProvDist.toString()).append(", ");
        strBuffer.append("telefonos = ").append(telefonos).append(", ");
        strBuffer.append("principal = ").append(principal).append(", ");
        strBuffer.append("activo = ").append(activo).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.nombre = nombre.toUpperCase();
        this.direccion = direccion.toUpperCase();
        this.urbanizacion = urbanizacion.toUpperCase();
        this.telefonos = telefonos.toUpperCase();
        this.principal = principal.toUpperCase();
        this.activo = activo.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.nombre = nombre.toLowerCase();
        this.direccion = direccion.toLowerCase();
        this.urbanizacion = urbanizacion.toLowerCase();
        this.telefonos = telefonos.toLowerCase();
        this.principal = principal.toLowerCase();
        this.activo = activo.toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sucursal other = (Sucursal) obj;
        if (this.sucursalId != other.sucursalId && (this.sucursalId == null || !this.sucursalId.equals(other.sucursalId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.sucursalId != null ? this.sucursalId.hashCode() : 0);
        return hash;
    }
}
