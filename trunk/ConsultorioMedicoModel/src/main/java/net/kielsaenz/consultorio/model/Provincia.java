package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Provincia")
@NamedQueries( {
		@NamedQuery(name = "Provincia.getProvinciaPorId", query = "SELECT p FROM Provincia p WHERE  p.departamentoId = :departamentoId AND p.provinciaId = :provinciaId ORDER BY p.nombre ASC"),
		@NamedQuery(name = "Provincia.getProvinciasPorDepartamento", query = "SELECT p FROM Provincia p WHERE p.departamentoId = :departamentoId ORDER BY p.nombre ASC")})
public class Provincia {

	@Column(name="Departamento_Id")
	private String departamentoId;
	
	@Id
	@Column(name="Provincia_Id")
	private String provinciaId;
	
	@Column(name="Nombre")
	private String nombre;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
