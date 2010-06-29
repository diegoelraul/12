package net.kielsaenz.consultorio.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Kiel
 */
@Entity
@Table(name="DEP_PROV_DIST")
public class DepProvDist {

    @EmbeddedId
    private UbigeoId ubigeoId;
    @Column(name="DEPARTAMENTO", length=50, nullable=false)
    private String departamento;
    @Column(name="PROVINCIA", length=50, nullable=false)
    private String provincia;
    @Column(name="DISTRITO", length=50, nullable=false)
    private String distrito;

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public UbigeoId getUbigeoId() {
        return ubigeoId;
    }

    public void setUbigeoId(UbigeoId ubigeoId) {
        this.ubigeoId = ubigeoId;
    }
    
}
