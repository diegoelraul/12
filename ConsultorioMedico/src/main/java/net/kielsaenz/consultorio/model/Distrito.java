package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "DISTRITO")
@NamedQueries({
    @NamedQuery(name = "Distrito.getDistritoPorId", query = "SELECT d FROM Distrito d WHERE d.departamentoId = :departamentoId AND d.provinciaId = :provinciaId AND d.distritoId = :distritoId ORDER BY d.nombre ASC"),
    @NamedQuery(name = "Distrito.getDistritosPorProvincia", query = "SELECT d FROM Distrito d WHERE d.departamentoId = :departamentoId AND d.provinciaId = :provinciaId ORDER BY d.nombre ASC")})
public class Distrito extends Bean {

    @Column(name = "DEPARTAMENTO_ID")
    private String departamentoId;
    @Column(name = "PROVINCIA_ID")
    private String provinciaId;
    @Id
    @Column(name = "DISTRITO_ID")
    private String distritoId;
    @Column(name = "NOMBRE")
    private String nombre;

    public Distrito() {
        super();
    }

    public Distrito(String departamentoId, String provinciaId,
            String distritoId, String nombre) {
        this.departamentoId = departamentoId;
        this.provinciaId = provinciaId;
        this.distritoId = distritoId;
        this.nombre = nombre;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("departamentoId = ").append(departamentoId).append(", ");
        strBuffer.append("provinciaId = ").append(provinciaId).append(", ");
        strBuffer.append("distritoId = ").append(distritoId).append(", ");
        strBuffer.append("nombre = ").append(nombre).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.nombre = nombre.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.nombre = nombre.toLowerCase();
    }
}
