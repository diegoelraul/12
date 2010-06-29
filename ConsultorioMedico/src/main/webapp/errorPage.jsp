<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=getServletContext().getInitParameter("empresa")%> -
            Error</title>
            <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/css/principal.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/css/login.css" />
    </head>
    <body class="principal">
        <div id="main_login">
            <div class="login_bg">
                <div style="padding-left:30px; float:left; width:162px">
                    <a href="home.jsp"><img src="<%=request.getContextPath()%>/recursos/img/logo.png" alt="" title="" width="162" height="54" border="0" /></a>
                </div>
                <div style="float:right; padding-right:30px;"><img src="<%=request.getContextPath()%>/recursos/img/d001.gif" width="165" height="230" /></div>
                <div style="padding-left:30px; padding-top:70px; text-align:center">
                    <span class="msg01">
                        <span class="error">Error</span>
                        <br/>
                        <br/>
                        ${errorMsg}
                    </span>
                    <p/>Comun&iacute;quese con su Administrador de Sistemas.
                </div>
            </div>
            <div id="pie">
                <%@ include file="pie.jsp"%>
            </div>
        </div>
    </body>
</html>
