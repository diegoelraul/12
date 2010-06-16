package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SucursalId extends Bean{

	@Column(name="Empresa_Id")
	private Integer empresaId;
	
	@Column(name="Sucursal_Id")
	private Integer sucursalId;
	
	public SucursalId(Integer empresaId, Integer sucursalId){
		this.empresaId = empresaId;
		this.sucursalId = sucursalId;
	}
	
	public Integer getEmpresaId() {
		return empresaId;
	}
	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
}
