<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><script type="text/javascript" src="<s:url value='/resources/jss/calendario/calendar.js'/>"></script>

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
            $(function() {
                $('#btnNuevo').button({
                    icons: {
                        primary: 'ui-icon-document'
                    }
                });
                $('#btnModificar').button({
                    icons: {
                        primary: 'ui-icon-pencil'
                    }
                });
                $('#btnEliminar').button({
                    icons: {
                        primary: 'ui-icon-trash'
                    }
                });
            });
        </script>
    </head>
    <body class="principal">
        <div id="cabecera"></div>
        <div id="cuerpo">
            <div id="tituloFormulario">Mantenimiento de Sucursales</div>
            <br />
            <s:form namespace="/sucursal" name="frmSucursal" method="post" action="">
                <div id="botones" align="center" style="width: 1000px">
                    <div align="left">
                        <button id="btnNuevo" onclick="">Nuevo</button>
                        <button id="btnModificar" onclick="">Modificar</button>
                        <button id="btnEliminar" onclick="">Eliminar</button>
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
                            <td class="x4x"><input type="radio" name="rbtSucursal"
                                                   id="${sucursal.sucursalId}"
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