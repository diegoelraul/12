package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UbigeoId extends Bean{

	@Column(name="Departamento_Id")
    private String departamentoId;
	
	@Column(name="Provincia_Id")
    private String provinciaId;
    
	@Column(name="Distrito_Id")
    private String distritoId;
    
	public String getDepartamentoId() {
		return departamentoId;
	}
	public void setDepartamentoId(String departamentoId) {
		this.departamentoId = departamentoId;
	}
	public String getProvinciaId() {
		return provinciaId;
	}
	public void setProvinciaId(String provinciaId) {
		this.provinciaId = provinciaId;
	}
	public String getDistritoId() {
		return distritoId;
	}
	public void setDistritoId(String distritoId) {
		this.distritoId = distritoId;
	}
}
