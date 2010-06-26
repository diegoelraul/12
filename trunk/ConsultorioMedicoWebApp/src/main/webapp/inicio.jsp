<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=getServletContext().getInitParameter("empresa")%></title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/css/principal.css" />
    </head>
    <body class="principal">
        <div id="cabecera">
            <%@ include file="cabecera.jsp"%>
        </div>
        <div id="cuerpo">
            <a href="<%=request.getContextPath()%>/mantenimientos/MantEmpresa.jsp" >Empresa</a><br />
            <a href="<%=request.getContextPath()%>/mantenimientos/MantSucursal.jsp" >Sucursal</a><br />
        </div>
        <div id="pie">
            <%@ include file="pie.jsp"%>
        </div>
    </body>
</html>