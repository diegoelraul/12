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
<div id="cuerpo">
<div id="tituloFormulario">Mantenimiento de Sucursales</div>
<div id="datosFormulario">
<form id="form1" name="form1" method="post" action="">
<input type="hidden" id="empresaId" value=""/>
<table border="0" align="center">
	<tr>
		<td width="355"><label for="txtNombre">Nombre:</label> <input
			name="txtNombre" id="txtNombre" type="text" class="textoSimple" /></td>
		<td width="355"><label for="ckbPrincipal">Principal</label><input
			type="checkbox" name="ckbPrincipal" id="ckbPrincipal" /></td>
	</tr>
	<tr>
		<td colspan="2"><label for="txtDireccion">Dirección:</label> <input
			name="txtDireccion" id="txtDireccion" type="text" class="textoDoble" /></td>
	</tr>
	<tr>
		<td colspan="2"><label for="txtDireccion">Urbanización:</label> <input
			name="txtDireccion" id="txtDireccion" type="text" class="textoDoble" /></td>
	</tr>
	<tr>
		<td><label for="cboDepartamento">Departamento:</label> <select
			name="cboDepartamento" id="cboDepartamento" class="textoSimple">
			<option value="1">Stgo. de Surco</option>
		</select></td>
		<td><label for="cboProvincia">Provincia:</label> <select
			name="cboProvincia" id="cboProvincia" class="textoSimple">
			<option value="1">Stgo. de Surco</option>
		</select></td>
	</tr>
	<tr>
		<td><label for="cboDistrito">Distrito:</label> <select
			name="cboDistrito" id="cboDistrito" class="textoSimple">
			<option value="1">Stgo. de Surco</option>
		</select></td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><label for="txtTelefono">Tel&eacute;fonos:</label><input
			name="txtTelefono" id="txtTelefono" type="text" class="textoSimple" /></td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</div>
</div>
<div id="pie"><%@ include file="/pie.jsp"%>
</div>
</html>