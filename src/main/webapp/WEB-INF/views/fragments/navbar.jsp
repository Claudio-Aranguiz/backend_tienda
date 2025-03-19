<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand">Mi Aplicación</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
               <%--  <li class="nav-item ms-2">
                    <a class="nav-link"> En sesión: ${sessionScope.username}</a>
                </li> --%>
                <li class="nav-item ms-2">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Inicio</a>
                </li>
                <%-- <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/registro">Registrarse</a>
                </li> --%>
            </ul>
        </div>
    </div>
    <form class="d-flex px-2 mr-2 navbar-nav">           
        <%-- <a href="${pageContext.request.contextPath}/logout" class="nav-link">Salir</a> --%>
    </form>
</nav>