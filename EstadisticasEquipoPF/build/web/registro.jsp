<%-- 
    Document   : registro
    Created on : 4 may 2025, 13:27:03
    Author     : Oley
--%>

<%@page import="opciones.ControlUsuario"%>
<%@page import="opciones.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Registrar Nuevo Usuario</h2>
    <form method="POST">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required><br><br>

        <label for="correo">Correo electrónico:</label>
        <input type="email" id="correo" name="correo" required><br><br>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required><br><br>

        <label for="nombre">Nombre completo:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>

        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" required><br><br>

        <input type="submit" value="Registrar">
    </form>

    <%
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");
        String edadStr = request.getParameter("edad");

        if (usuario != null && correo != null && contrasena != null && nombre != null && edadStr != null) {
            int edad = Integer.parseInt(edadStr);
            int id = ControlUsuario.obtenerTodos().size() + 1; 
            Usuario nuevo = new Usuario(id, usuario, correo, contrasena, nombre, edad);
            ControlUsuario.agregarUsuario(nuevo);
            out.println("<p>¡Usuario registrado correctamente!</p>");
        }
    %>

    <p><a href="login.jsp">Ir al Login</a></p>
    <p><a href="index.jsp">Volver al Inicio</a></p>
</body>
</html>