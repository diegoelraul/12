package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Ubigeo")
public class Ubigeo extends Bean {

	@EmbeddedId
	private UbigeoId ubigeoId;

	@Column(name = "Nombre", nullable = false, length = 50)
	private String nombre;

	public UbigeoId getUbigeoId() {
		return ubigeoId;
	}

	public void setUbigeoId(UbigeoId ubigeoId) {
		this.ubigeoId = ubigeoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void toUpperCase(){
		this.nombre = nombre.toUpperCase();
	}
}
