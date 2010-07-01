<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><%=getServletContext().getInitParameter("empresa")%> - Logout</title>
        <link rel="stylesheet" type="text/css" href="<s:url value='/resources/css/style.css'/>" media="screen" />
    </head>
    <body class="principal">
        <div id="main_login">
            <div class="login_bg">
                <div style="padding-left:30px; float:left; width:162px">
                    <a href="home.jsp">
                        <img src="<s:url value='/recursos/img/logo.png' />" width="162" height="54" border="0" />
                    </a>
                </div>
                <div style="float:right; padding-right:30px;">
                    <img src="<s:url value='/recursos/img/d001.gif' />" width="165" height="230" />
                </div>
                <div style="padding-left:30px; padding-top:90px; text-align:center">
                    <span class="msg01">Gracias por utilizar nuestros servicios...</span>
                </div>
            </div>
            <div id="footer_loguin">&copy; Medical Clinic 2008. All Rights Reserved</div>
        </div>
    </body>
</html>
