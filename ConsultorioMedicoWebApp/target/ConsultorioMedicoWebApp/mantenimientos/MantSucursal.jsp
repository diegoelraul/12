<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mantenimiento de Sucursal</title>
<link type="text/css"
	href="<%=request.getContextPath()%>/recursos/css/principal.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/recursos/js/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/recursos/js/jquery/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/recursos/js/jquery/ui/jquery.ui.widget.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/recursos/js/jquery/ui/jquery.ui.button.js"></script>
<link type="text/css"
	href="<%=request.getContextPath()%>/recursos/css/jquery/demos.css"
	rel="stylesheet" />
</head>
<body id="body">
<div id="cabecera"></div>
<div id="cuerpo">
<div id="tituloFormulario">Mantenimiento de Sucursales</div>
<form id="form1" name="form1" method="post" action="">
<table border="0" align="center">
	<tr>
		<th width="25" scope="col">&nbsp;</th>
		<th width="100" scope="col">Nombre</th>
		<th width="250" scope="col">Dirección</th>
		<th width="120" scope="col">Distrito</th>
		<th width="120" scope="col">Provincia</th>
		<th width="120" scope="col">Departamento</th>
		<th width="120" scope="col">Teléfonos</th>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</div>
<div id="pie"><%@ include file="/pie.jsp"%>
</div>
</html>