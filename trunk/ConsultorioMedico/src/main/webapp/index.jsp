<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=getServletContext().getInitParameter("empresa")%></title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/css/principal.css" />
    </head>
    <body class="principal">
        <div id="cabecera"></div>
        <div id="cuerpo">
            <h1>Consultorio Médico</h1>
            <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
        </div>
        <div id="pie">
            <%@ include file="pie.jsp"%>
        </div>
    </body>
</html>
