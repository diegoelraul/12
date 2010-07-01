<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=getServletContext().getInitParameter("empresa")%> - Sucursal</title>
        <link type="text/css"
              href="<%=request.getContextPath()%>/recursos/css/principal.css"
              rel="stylesheet" />
        <link type="text/css"
              href="<%=request.getContextPath()%>/recursos/css/jquery/redmond/jquery.ui.all.css"
              rel="stylesheet" />
        <script type="text/javascript"
        src="<s:url value='/recursos/js/jquery/jquery-1.4.2.js'/>"></script>
        <script type="text/javascript"
        src="<s:url value='/recursos/js/jquery/ui/jquery.ui.core.js'/>"></script>
        <script type="text/javascript"
        src="<s:url value='/recursos/js/jquery/ui/jquery.ui.widget.js'/>"></script>
        <script type="text/javascript"
        src="<s:url value='/recursos/js/jquery/ui/jquery.ui.button.js'/>"></script>
        <link type="text/css"
              href="<s:url value='/recursos/css/jquery/demos.css'/>"
              rel="stylesheet" />
        <script type="text/javascript">
            function btnNuevo_onclick(){
                document.getElementById('frmSucursal').action="<s:url value='/sucursal/sucursalNuevo.action'/>";
                return true;
            }
            function btnModificar_onclick(){
                document.getElementById('frmSucursal').action="<s:url value='/sucursal/sucursalModificar.action'/>";
                return true;
            }
            function btnEliminar_onclick(){
                document.getElementById('frmSucursal').action="<s:url value='/sucursal/sucursalEliminar.action'/>";
                return true;
            }
        </script>
    </head>
    <body class="principal">
        <div id="cabecera"></div>
        <div id="cuerpo">
            <div id="tituloFormulario">Mantenimiento de Sucursales</div>
            <br />
            <s:form namespace="/sucursal" id="frmSucursal" name="frmSucursal" method="post" action="">
                <s:token />
                <div id="botones" align="center" style="width: 1000px">
                    <div align="left">
                        <s:submit id="btnNuevo" name="btnNuevo" onclick="javascript:btnNuevo_onclick();" value="Nuevo" />&nbsp;
                        <s:submit id="btnModificar" name="btnModificar" onclick="javascript:btnModificar_onclick();" value="Modificar" />&nbsp;
                        <s:submit id="btnEliminar" name="btnEliminar" onclick="javascript:btnEliminar_onclick();" value="Eliminar" />&nbsp;
                        <s:actionmessage />&nbsp;
                        <s:fielderror />&nbsp;
                        <span class="errorMessage">${errorMsg}</span>&nbsp;
                        <span class="okMessage">${okMsg}</span>&nbsp;
                    </div>
                </div>
                <br />
                <table class="x1h" border="0" align="center">
                    <tr>
                        <th class="x4j" width="25" scope="col">&nbsp;</th>
                        <th class="x4j" width="80" scope="col">Nombre</th>
                        <th class="x4j" width="150" scope="col">Dirección</th>
                        <th class="x4j" width="150" scope="col">Urbanización</th>
                        <th class="x4j" width="180" scope="col">Distrito</th>
                        <th class="x4j" width="90" scope="col">Provincia</th>
                        <th class="x4j" width="90" scope="col">Departamento</th>
                        <th class="x4j" width="120" scope="col">Teléfonos</th>
                        <th class="x4j" width="50" scope="col">Princ.</th>
                        <th class="x4j" width="50" scope="col">Activo</th>
                    </tr>
                    <c:if test="${empresa.sucursales}==0">
                        <tr>
                            <td class="x4x" colspan="9">Aún no se ha registrado ninguna sucursal.</td>
                        </tr>
                    </c:if>
                    <c:forEach var="sucursal" items="${empresa.sucursales}">
                        <tr>
                            <td class="x4x"><input type="radio" name="sucursalId"
                                                   id="sucursalId"
                                                   value="${sucursal.sucursalId}"/></td>
                            <td class="x4x">${sucursal.nombre}</td>
                            <td class="x4x">${sucursal.direccion}</td>
                            <td class="x4x">${sucursal.urbanizacion}</td>
                            <td class="x4x">${sucursal.depProvDist.distrito}</td>
                            <td class="x4x">${sucursal.depProvDist.provincia}</td>
                            <td class="x4x">${sucursal.depProvDist.departamento}</td>
                            <td class="x4x">${sucursal.telefonos}</td>
                            <td class="x4x">${sucursal.principal}</td>
                            <td class="x4x">${sucursal.activo}</td>
                        </tr>
                    </c:forEach>
                </table>
            </s:form>
        </div>
        <div id="pie"><%@ include file="/pie.jsp"%>
        </div>
</html>