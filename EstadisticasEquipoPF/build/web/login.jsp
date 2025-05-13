<%-- 
    Document   : login
    Created on : 4 may 2025, 13:30:06
    Author     : Oley
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Iniciar Sesión</h2>

    <form action="login.jsp" method="POST">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required><br><br>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required><br><br>

        <button type="submit">Ingresar</button>
    </form>

    <%
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        if (usuario != null && contrasena != null) {
            if (usuario.equals("admin") && contrasena.equals("admin")) {
                response.sendRedirect("usuarios.jsp");
            } else {
                out.println("<p style='color:red;'>Acceso denegado: usuario o contraseña incorrectos.</p>");
            }
        }
    %>

    <p><a href="index.jsp">← Volver</a></p>
</body>
</html>
