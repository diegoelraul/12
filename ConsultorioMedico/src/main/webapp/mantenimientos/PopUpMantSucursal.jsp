<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Mantenimiento de Sucursal</title>
        <link type="text/css"
              href="<%=request.getContextPath()%>/recursos/css/principal.css"
              rel="stylesheet" />
        <script type="text/javascript">
            function ubigeo_onchange () {
                frmSucursal.action = '<s:url value="/sucursal/sucursalNuevo.action"/>';
                frmSucursal.submit();
            }
            function grabar () {
                frmSucursal.action = '<s:url value="/sucursal/sucursalInsertar.action"/>';
                frmSucursal.submit();
            }
        </script>

    </head>
    <body class="principal">
        <div id="cuerpo">
            <div id="tituloFormulario">Mantenimiento de Sucursales</div>
            <div id="datosFormulario">
                <s:form id="frmSucursal" name="frmSucursal" method="post" action="" namespace="\sucursal">
                    <s:token />
                    <input type="hidden" id="empresaId" value=""/>
                    <table border="0" align="center">
                        <tr>
                            <td width="355"><label for="nombre">Nombre:</label> <input
                                    name="nombre" id="nombre" type="text" class="textoSimple" value="${nombre}"/></td>
                            <td width="355"><label for="principal">Principal</label><input
                                    type="checkbox" name="principal" id="principal" 
                                    <c:if test="${principal}">
                                        checked="checked"
                                    </c:if>/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><label for="direccion">Dirección:</label> <input
                                    name="direccion" id="direccion" type="text" class="textoDoble" value="${direccion}"/></td>
                        </tr>
                        <tr>
                            <td colspan="2"><label for="urbanizacion">Urbanización:</label> <input
                                    name="urbanizacion" id="urbanizacion" type="text" class="textoDoble" value="${urbanizacion}"/></td>
                        </tr>
                        <tr>
                            <td><div class="ui-widget">
                                    <label for="departamentoId">Departamento:</label> 
                                    <select name="departamentoId" id="departamentoId" class="textoSimple"
                                            onchange="ubigeo_onchange()">
                                        <c:forEach var="departamento" items="${departamentos}">
                                            <option value="${departamento.departamentoId}"
                                                    <c:if test="${departamento.departamentoId == departamentoId}">
                                                        selected="selected"
                                                    </c:if> >${departamento.nombre}</option>
                                        </c:forEach>
                                    </select></div></td>
                            <td><label for="provinciaId">Provincia:</label> <select
                                    name="provinciaId" id="provinciaId" class="textoSimple"
                                    onchange="ubigeo_onchange()">
                                    <c:forEach var="provincia" items="${provincias}">
                                        <option value="${provincia.provinciaId}"
                                                <c:if test="${provincia.provinciaId == provinciaId}">
                                                    selected="selected"
                                                </c:if> >${provincia.nombre}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td colspan="2"><label for="distritoId">Distrito:</label> <select
                                    name="distritoId" id="distritoId" class="textoDoble">
                                    <c:forEach var="distrito" items="${distritos}">
                                        <option value="${distrito.distritoId}"
                                                <c:if test="${distrito.distritoId == distritoId}">
                                                    selected="selected"
                                                </c:if> >${distrito.nombre}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td><label for="telefonos">Tel&eacute;fonos:</label><input
                                    name="telefonos" id="telefonos" type="text" class="textoSimple" value="${telefonos}"/></td>
                            <td><label for="activo">Activo</label><input
                                    type="checkbox" name="activo" id="activo" 
                                    <c:if test="${activo}">
                                        checked="checked"
                                    </c:if>/></td>
                        </tr>
                    </table>
                    <s:submit name="btnEnviar" value="Enviar" cssClass="x7g" onclick="grabar()"
                              cssStyle="BACKGROUND-IMAGE: url(recursos/img/btn-bg1.gif)" />
                </s:form>
            </div>
        </div>
        <div id="pie"><%@ include file="/pie.jsp"%>
        </div>
</html>