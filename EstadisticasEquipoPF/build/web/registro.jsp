<%-- 
    Document   : registro
    Created on : 4 may 2025, 13:27:03
    Author     : Oley
--%>

<%@page import="opciones.ControlUsuario"%>
<%@page import="opciones.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Registro</title></head>
<body>
    <h2>Registrar nuevo usuario</h2>
    <form method="POST">
        Usuario: <input type="text" name="usuario" required><br>
        Correo: <input type="email" name="correo" required><br>
        Contraseña: <input type="password" name="contrasena" required><br>
        Nombre: <input type="text" name="nombre" required><br>
        Edad: <input type="number" name="edad" min="1" required><br>
        Tipo: 
        <select name="tipo" required>
            <option value="usuario">Usuario</option>
            <option value="admin">Admin</option>
        </select><br><br>
        <input type="submit" value="Registrar">
    </form>

    <%
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");
        String edadStr = request.getParameter("edad");
        String tipo = request.getParameter("tipo");

        if (usuario != null && correo != null && contrasena != null && nombre != null && edadStr != null && tipo != null) {
            int edad = Integer.parseInt(edadStr);
            int id = ControlUsuario.obtenerLista().size() + 1; 
            Usuario nuevo = new Usuario(id, usuario, correo, contrasena, nombre, edad, tipo);
            ControlUsuario.agregarUsuarios(nuevo);
            out.println("<p>¡Usuario registrado correctamente!</p>");
        }
    %>

    <a href="login.jsp">Ir al login</a>
</body>
</html>
