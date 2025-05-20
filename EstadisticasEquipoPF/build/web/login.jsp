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
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>

<div class="contenedor-login">
    <h2>Iniciar Sesión</h2>

    <form action="LoginServlet" method="POST" class="form-login">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>

        <button type="submit" class="btn-ingresar">Ingresar</button>
    </form>

    <% 
        String error = request.getParameter("error");
        if ("usuario".equals(error)) {
    %>
        <p class="mensaje-error">Usuario no encontrado.</p>
    <% } else if ("contrasena".equals(error)) { %>
        <p class="mensaje-error">Contraseña incorrecta.</p>
    <% } else if ("conexion".equals(error)) { %>
        <p class="mensaje-error">Error al conectar con la base de datos.</p>
    <% } %>

    <p><a href="index.jsp" class="link-volver">← Volver al inicio</a></p>
</div>

</body>
</html>
