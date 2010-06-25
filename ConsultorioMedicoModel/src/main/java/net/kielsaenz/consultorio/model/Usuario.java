package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Hace referencia al usuario de sistema
 * @author dew - Grupo 04
 */
@Entity
@Table(name="USUARIO")
public class Usuario extends Bean {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="USUARIO_Id", nullable=false)
    private Integer usuarioId;

    @ManyToOne(targetEntity=Empresa.class, optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="EMPRESA_ID", nullable=false)
    private Empresa empresa;

    @Column(name="USERNAME", nullable=false, length=20)
    private String username;

    @Column(name="PASSWORD", nullable=false, length=20)
    private String password;

    /*@ManyToOne(targetEntity=Persona.class, optional=false)
    @JoinColumn(name="PERSONA_ID", nullable=false)
    private Persona persona;*/
    
    @Column(name="ACTIVO", nullable=false, length=1)
	private String activo;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
/*
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
*/
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
}
