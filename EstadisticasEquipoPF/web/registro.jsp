<%-- 
    Document   : registro
    Created on : 4 may 2025, 13:27:03
    Author     : Oley
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
    <link rel="stylesheet" href="index.css">
</head>
<body>

<div class="contenedor">
    <h2>Registrar Nuevo Usuario</h2>

    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        String error = (String) request.getAttribute("error");
        if (mensaje != null && !mensaje.isEmpty()) {
    %>
        <p class="mensaje-exito"><%= mensaje %></p>
    <% 
        } 
        if (error != null && !error.isEmpty()) {
    %>
        <p class="mensaje-error"><%= error %></p>
    <% } %>

    <form method="POST" action="RegistroUsuarioServlet" class="formulario">
        <input type="hidden" name="accion" value="agregar">

        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required>

        <label for="correo">Correo electrónico:</label>
        <input type="email" id="correo" name="correo" required>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>

        <label for="nombre">Nombre completo:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" required>

        <input type="submit" value="Registrar" class="boton">
    </form>

    <p><a href="index.jsp" class="boton">Volver al Inicio</a></p>
</div>

</body>
</html>
