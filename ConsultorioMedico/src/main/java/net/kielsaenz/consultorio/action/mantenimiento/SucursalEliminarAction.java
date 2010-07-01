package net.kielsaenz.consultorio.action.mantenimiento;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import javax.servlet.http.HttpSession;
import net.kielsaenz.consultorio.action.BaseAction;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.service.EmpresaService;
import net.kielsaenz.consultorio.service.SucursalService;
import net.kielsaenz.consultorio.service.implementacion.SucursalServiceImpl;
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
    @Result(name = "success", value = "/mantenimientos/MantSucursal.jsp", type = ServletDispatcherResult.class),
    @Result(name = "input", value = "/mantenimientos/MantSucursal.jsp", type = ServletDispatcherResult.class)
})
public class SucursalEliminarAction extends BaseAction {

    private Integer sucursalId;
    private SucursalService sucursalService;
    private EmpresaService empresaService;

    @Override
    public String execute() throws Exception {
        try {
            System.out.println("capturando sucursal");
            Sucursal sucursal = sucursalService.getSucursalPorId(sucursalId);
            System.out.println("sucursal capturada");
            if (sucursalService.eliminar(sucursal)) {
                System.out.println("sucursal elimininada");
                //Se obtiene la sesion
                HttpSession session = request.getSession(false);
                if (session != null) {
                    System.out.println("sesion capturada");
                    // Se obtiene la empresa
                    Empresa empresa = empresaService.getEmpresaPorRUC(
                            session.getServletContext().getInitParameter("empresa-ruc"));
                    System.out.println("empresa capturada");
                    session.removeAttribute("empresa");
                    System.out.println("empresa de sesion removida");
                    session.setAttribute("empresa", empresa);
                    System.out.println("empresa ingresada a la sesion");
                    request.setAttribute("okMsg", "Se eliminó exitosamente la sucursal.");
                    System.out.println("sucursales de la empresa: " + empresa.getSucursales().size());
                    return SUCCESS;
                } else {
                    System.out.println("sesion no encontrada");
                    request.setAttribute("errorMsg", "No se pudo actualizar la empresa porque no se pudo recuperar la sesión.");
                    request.setAttribute("action", "/logeo/login.action");
                    return ERROR;
                }
            } else {
                System.out.println("sucursal no eliminada");
                request.setAttribute("errorMsg", "No se pudo ingresar la sucursal.");
                return INPUT;
            }
        } catch (Exception ex) {
            System.out.println("error");
            ex.printStackTrace();
            request.setAttribute("errorMsg", ex.getMessage());
            return INPUT;
        }
    }

    public Integer getSucursalId() {
        return sucursalId;
    }

    @RequiredFieldValidator(message = "Por favor, seleccione una sucursal.")
    public void setSucursalId(Integer sucursalId) {
        this.sucursalId = sucursalId;
    }


    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public void setSucursalService(SucursalServiceImpl sucursalService) {
        this.sucursalService = sucursalService;
    }
}
