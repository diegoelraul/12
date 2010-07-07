package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PROVINCIA")
@NamedQueries({
    @NamedQuery(name = "Provincia.getProvinciaPorId", query = "SELECT p FROM Provincia p WHERE  p.departamentoId = :departamentoId AND p.provinciaId = :provinciaId ORDER BY p.nombre ASC"),
    @NamedQuery(name = "Provincia.getProvinciasPorDepartamento", query = "SELECT p FROM Provincia p WHERE p.departamentoId = :departamentoId ORDER BY p.nombre ASC")})
public class Provincia extends Bean {

    @Column(name = "DEPARTAMENTO_ID")
    private String departamentoId;
    @Id
    @Column(name = "PROVINCIA_ID")
    private String provinciaId;
    @Column(name = "NOMBRE")
    private String nombre;

    public Provincia() {
        super();
    }

    public Provincia(String departamentoId, String provinciaId, String nombre) {
        this.departamentoId = departamentoId;
        this.provinciaId = provinciaId;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Provincia other = (Provincia) obj;
        if ((this.departamentoId == null) ? (other.departamentoId != null) : !this.departamentoId.equals(other.departamentoId)) {
            return false;
        }
        if ((this.provinciaId == null) ? (other.provinciaId != null) : !this.provinciaId.equals(other.provinciaId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.departamentoId != null ? this.departamentoId.hashCode() : 0);
        hash = 89 * hash + (this.provinciaId != null ? this.provinciaId.hashCode() : 0);
        return hash;
    }
}
