package net.kielsaenz.consultorio.action.mantenimiento;

import java.util.List;
import net.kielsaenz.consultorio.action.BaseAction;
import net.kielsaenz.consultorio.model.Departamento;
import net.kielsaenz.consultorio.model.Distrito;
import net.kielsaenz.consultorio.model.Provincia;
import net.kielsaenz.consultorio.service.UbigeoService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletDispatcherResult;

/**
 *
 * @author Kiel
 */
@Namespace(value = "/sucursal")
@ParentPackage(value = "consultorio")
@Results({
    @Result(name = "success", value = "/mantenimientos/PopUpMantSucursal.jsp", type = ServletDispatcherResult.class)
})
public class SucursalNuevoAction extends BaseAction {

    private UbigeoService ubigeoService;
    private String nombre;
    private String direccion;
    private String urbanizacion;
    private String departamentoId;
    private String provinciaId;
    private String distritoId;
    private String telefonos;
    private Boolean principal;
    private Boolean activo;

    @Override
    public String execute() throws Exception {
        try {
            System.out.println("activo req: " + request.getAttribute("activo"));
            System.out.println("principal req: " + request.getAttribute("principal"));
            List<Departamento> departamentos = ubigeoService.getDepartamentos();
            request.setAttribute("departamentos", departamentos);
            if (departamentos.size() > 0) {
                Departamento departamento;
                if (StringUtils.isBlank(departamentoId)) {
                    departamento = departamentos.get(0);
                    departamentoId = departamento.getDepartamentoId();
                } else {
                    departamento = ubigeoService.getDepartamentoPorId(departamentoId);
                }
                request.setAttribute("departamentoId", departamentoId);
                List<Provincia> provincias = ubigeoService.getProvinciasPorDepartamento(departamento);
                request.setAttribute("provincias", provincias);
                if (provincias.size() > 0) {
                    Provincia provincia;
                    if (StringUtils.isBlank(provinciaId)) {
                        provincia = provincias.get(0);
                        provinciaId = provincia.getProvinciaId();
                    } else {
                        provincia = ubigeoService.getProvinciaPorId(departamentoId, provinciaId);
                    }
                    request.setAttribute("provinciaId", provinciaId);
                    List<Distrito> distritos = ubigeoService.getDistritosPorProvincia(provincia);
                    request.setAttribute("distritos", distritos);
                    if (distritos.size() > 0) {
                        Distrito distrito;
                        if (StringUtils.isBlank(distritoId)) {
                            distrito = distritos.get(0);
                            distritoId = distrito.getDistritoId();
                        } else {
                            distrito = ubigeoService.getDistritoPorId(departamentoId, provinciaId, distritoId);
                        }
                        request.setAttribute("distritoId", distritoId);
                    }
                }
            }
            request.setAttribute("nombre", nombre);
            request.setAttribute("direccion", direccion);
            request.setAttribute("urbanizacion", urbanizacion);
            request.setAttribute("telefonos", telefonos);
            request.setAttribute("principal", principal);
            request.setAttribute("activo", activo);
            System.out.println("principal: " + principal);
            System.out.println("activo: " + activo);
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("errorMsg", ex.getMessage());
            return ERROR;
        }
    }

    public UbigeoService getUbigeoService() {
        return ubigeoService;
    }

    public void setUbigeoService(UbigeoService ubigeoService) {
        this.ubigeoService = ubigeoService;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(String provinciaId) {
        this.provinciaId = provinciaId;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }
}
