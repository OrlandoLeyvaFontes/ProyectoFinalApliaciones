<%-- 
    Document   : registro
    Created on : 4 may 2025, 13:27:03
    Author     : Oley
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuario</title>
</head>
<body>
    <h2>Registrar Nuevo Usuario</h2>

    <!-- Mostrar el mensaje de éxito si está presente -->
    <c:if test="${not empty mensaje}">
        <p style="color: green;">${mensaje}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    
    <form method="POST" action="RegistroUsuarioServlet">
        <input type="hidden" name="accion" value="agregar">
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

    <p><a href="login.jsp">Ir al Login</a></p>
    <p><a href="index.jsp">Volver al Inicio</a></p>
</body>
</html>
