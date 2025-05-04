<%-- 
    Document   : login
    Created on : 4 may 2025, 13:30:06
    Author     : Oley
--%>

<%@page import="opciones.ControlUsuario"%>
<%@page import="opciones.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
    <h2>Iniciar sesion</h2>
    <form action="login.jsp" method="POST">
        Usuario: <input type="text" name="usuario"><br>
        Contraseña: <input type="password" name="contrasena"><br>
        <input type="submit" value="Ingresar">
    </form>

    <%
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        if (usuario != null && contrasena != null) {
            Usuario u = ControlUsuario.buscarUsuario(usuario, contrasena);
            if (u != null && u.getTipo().equals("admin")) {
                response.sendRedirect("usuarios.jsp");
            } else {
                out.println("<p>Acceso denegado o usuario no es administrador</p>");
            }
        }
    %>
</body>
</html>
