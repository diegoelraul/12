<div class="header">
    <div id="logo">
        <a href="home.jsp">
            <img src="<%=request.getContextPath()%>/recursos/img/logo.png"
                 alt="" title="" width="162" height="54" border="0" />
        </a>
    </div>
    <div class="right_header">
        <div class="top_menu">
            <c:if test="${usuario != null}">
                <a href="#">${usuario.username}</a>
                <a href="#">Cerrar Sesión</a>
            </c:if>
        </div>
    </div>
</div>
