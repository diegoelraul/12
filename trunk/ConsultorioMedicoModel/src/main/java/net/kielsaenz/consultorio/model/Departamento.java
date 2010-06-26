package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTAMENTO")
@NamedQueries({
    @NamedQuery(name = "Departamento.getDepartamentoPorId", query = "SELECT d FROM Departamento d WHERE d.departamentoId = :departamentoId ORDER BY d.nombre ASC"),
    @NamedQuery(name = "Departamento.getDepartamentos", query = "SELECT d FROM Departamento d ORDER BY d.nombre ASC")})
public class Departamento {

    @Id
    @Column(name = "DEPARTAMENTO_ID")
    private String departamentoId;
    @Column(name = "NOMBRE")
    private String nombre;

    public Departamento() {
        super();
    }

    public Departamento(String departamentoId, String nombre) {
        super();
        this.departamentoId = departamentoId;
        this.nombre = nombre;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
