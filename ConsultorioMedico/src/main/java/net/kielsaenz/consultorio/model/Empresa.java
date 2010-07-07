package net.kielsaenz.consultorio.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESA")
public class Empresa extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPRESA_ID")
    private Integer empresaId;
    @Column(name = "RAZON_SOCIAL", nullable = false, length = 50)
    private String razonSocial;
    @Column(name = "RUC", nullable = false, length = 11, unique = true)
    private String ruc;
    @Column(name = "ACTIVO", nullable = false, length = 1)
    private String activo;
    @OneToMany(targetEntity = Sucursal.class, mappedBy = "empresa", fetch = FetchType.EAGER)
    @OrderBy("nombre ASC")
    private List<Sucursal> sucursales;

    public Empresa() {
        super();
    }

    public Empresa(Integer empresaId, String razonSocial, String ruc,
            String activo, List<Sucursal> sucursales) {
        this.empresaId = empresaId;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.activo = activo;
        this.sucursales = sucursales;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public String toString() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append("clase: ").append(getReferencia()).append("{");
        strBuffer.append("empresaId = ").append(empresaId).append(", ");
        strBuffer.append("razonSocial = ").append(razonSocial).append(", ");
        strBuffer.append("ruc = ").append(ruc).append(", ");
        strBuffer.append("activo = ").append(activo).append("}");
        return strBuffer.toString();
    }

    @Override
    public void toUpperCase() {
        this.razonSocial = razonSocial.toUpperCase();
        this.ruc = ruc.toUpperCase();
        this.activo = activo.toUpperCase();
    }

    @Override
    public void toLowerCase() {
        this.razonSocial = razonSocial.toLowerCase();
        this.ruc = ruc.toLowerCase();
        this.activo = activo.toLowerCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        if (this.empresaId != other.empresaId && (this.empresaId == null || !this.empresaId.equals(other.empresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.empresaId != null ? this.empresaId.hashCode() : 0);
        return hash;
    }
}
