<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=getServletContext().getInitParameter("empresa")%> -
            Login</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/css/principal.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/css/login.css" />
    </head>
    <body class="principal">
        <div id="main_login">
            <div class="login_bg">
                <div class="title_login">
                    <div class="form_login">
                        <s:form action="login" namespace="/logeo"
                              method="POST" name="frmLogin">
                            <table border="0" cellpadding="0" cellspacing="0" align="center"
                                   width="210px">
                                <tr>
                                    <td>&nbsp;<s:textfield label="Usuario" name="username"
                                                 cssClass="x4" cssStyle="width:150px" /></td>
                                </tr>
                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td>&nbsp;<s:password label="Password" name="password"
                                                cssClass="x4" cssStyle="width:150px" /></td>
                                </tr>
                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="rigth">
                                        <s:submit name="btnEnviar"
                                                  value="Enviar"
                                                  cssClass="x7g"
                                                  cssStyle="BACKGROUND-IMAGE: url(recursos/img/btn-bg1.gif)" />
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <table border="0" cellpadding="0" cellspacing="0" align="center"
                                   width="300px">
                                <tr>
                                    <td colspan="3"><span class="errorMessage">${errorMsg}</span></td>
                                </tr>
                            </table>
                        </s:form>
                    </div>
                </div>
            </div>
            <div id="pie">
                <%@ include file="pie.jsp"%>
            </div>
        </div>
    </body>
</html>