package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SUCURSAL")
public class Sucursal extends Bean{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUCURSAL_ID")
	private Integer sucursalId;
	
	@ManyToOne(targetEntity=Empresa.class, optional=false)
    @JoinColumn(name="EMPRESA_ID")
	private Empresa empresa;
	
	@Column(name="NOMBRE", nullable=false, length=20)
	private String nombre;
	
	@Column(name="DIRECCION", nullable=false, length=40)
	private String direccion;
	
	@Column(name="URBANIZACION", nullable=true, length=25)
	private String urbanizacion;
    
	@Embedded
	private UbigeoId ubigeoId;
    
	@Column(name="TELEFONOS", nullable=true, length=20)
	private String telefonos;
	
	@Column(name="PRINCIPAL", nullable=false, length=1)
	private String principal;
	
	@Column(name="ACTIVO", nullable=false, length=1)
	private String activo;
	
	public Sucursal() {
		super();
	}

	public Sucursal(Integer sucursalId, Empresa empresa, String nombre,
			String direccion, String urbanizacion, UbigeoId ubigeoId,
			String telefonos, String principal, String activo) {
		super();
		this.sucursalId = sucursalId;
		this.empresa = empresa;
		this.nombre = nombre;
		this.direccion = direccion;
		this.urbanizacion = urbanizacion;
		this.ubigeoId = ubigeoId;
		this.telefonos = telefonos;
		this.principal = principal;
		this.activo = activo;
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

	public UbigeoId getUbigeoId() {
		return ubigeoId;
	}
	
	public void setUbigeoId(UbigeoId ubigeoId) {
		this.ubigeoId = ubigeoId;
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
	
	public void toUpperCase(){
		this.nombre = nombre.toUpperCase();
		this.direccion = direccion.toUpperCase();
		this.urbanizacion = urbanizacion.toUpperCase();
		this.telefonos = telefonos.toUpperCase();
		this.principal = principal.toUpperCase();
		this.activo = activo.toUpperCase();
	}
}
