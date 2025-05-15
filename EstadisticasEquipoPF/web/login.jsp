<%-- 
    Document   : login
    Created on : 4 may 2025, 13:30:06
    Author     : Oley
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Iniciar Sesión</h2>

    <form action="LoginServlet" method="POST">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required><br><br>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required><br><br>

        <button type="submit">Ingresar</button>
    </form>

    <% 
        String error = request.getParameter("error");
        if ("usuario".equals(error)) {
    %>
        <p style='color:red;'>Usuario no encontrado.</p>
    <% } else if ("contrasena".equals(error)) { %>
        <p style='color:red;'>Contraseña incorrecta.</p>
    <% } else if ("conexion".equals(error)) { %>
        <p style='color:red;'>Error al conectar con la base de datos.</p>
    <% } %>

    <p><a href="index.jsp">← Volver</a></p>
</body>
</html>
