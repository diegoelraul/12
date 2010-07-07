package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UBIGEO")
public class Ubigeo extends Bean {

    @EmbeddedId
    private UbigeoPK ubigeoPK;
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    public Ubigeo() {
        super();
    }

    public Ubigeo(UbigeoPK ubigeoPK, String nombre) {
        this.ubigeoPK = ubigeoPK;
        this.nombre = nombre;
    }

    public UbigeoPK getUbigeoPK() {
        return ubigeoPK;
    }

    public void setUbigeoId(UbigeoPK ubigeoPK) {
        this.ubigeoPK = ubigeoPK;
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
        strBuffer.append("ubigeoPK = ").append(ubigeoPK.toString()).append(", ");
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
        final Ubigeo other = (Ubigeo) obj;
        if (this.ubigeoPK != other.ubigeoPK && (this.ubigeoPK == null || !this.ubigeoPK.equals(other.ubigeoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (this.ubigeoPK != null ? this.ubigeoPK.hashCode() : 0);
        return hash;
    }
}
