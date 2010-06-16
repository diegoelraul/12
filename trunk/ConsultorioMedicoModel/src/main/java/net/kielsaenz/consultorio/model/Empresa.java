package net.kielsaenz.consultorio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="Empresa")
public class Empresa extends Bean{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Empresa_ID")
	private Integer empresaId;
	
	@Column(name="Razon_Social", nullable=false, length=50)
	private String razonSocial;
	
	@Column(name="RUC", nullable=false, length=11, unique=true)
	private String ruc;

	@Column(name="activo", nullable=false, length=1)
	private String activo;
	
	@OneToMany(mappedBy="empresa")
	@OrderBy("nombre ASC")
	private List<Sucursal> sucursales;
	
	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
}
