package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sucursal")
public class Sucursal extends Bean{

	@EmbeddedId
	private SucursalId sucursalId;
	
	@ManyToOne(targetEntity=net.kielsaenz.consultorio.model.Empresa.class, optional=false)
    @JoinColumn(name="empresaId")
	private Empresa empresa;
	
	@Column(name="nombre", nullable=false, length=20)
	private String nombre;
	
	@Column(name="Direccion", nullable=false, length=40)
	private String direccion;
	
	@Column(name="Urbanizacion", nullable=true, length=25)
	private String urbanizacion;
    
	@Embedded
	private UbigeoId ubigeoId;
    
	@Column(name="Telefonos", nullable=true, length=20)
	private String telefonos;
	
	@Column(name="Principal", nullable=false, length=1)
	private String principal;
	
	@Column(name="Activo", nullable=false, length=1)
	private String activo;
	
	public SucursalId getSucursalId() {
		return sucursalId;
	}
	
	public void setSucursalId(SucursalId sucursalId) {
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
}
