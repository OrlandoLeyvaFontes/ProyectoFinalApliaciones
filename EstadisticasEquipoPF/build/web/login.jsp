<%-- 
    Document   : login
    Created on : 4 may 2025, 13:30:06
    Author     : Oley
--%>

<%@page import="conexion.Conexion"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mongodb.client.*" %>
<%@ page import="org.bson.Document" %>
<%@ page import="com.mongodb.client.model.Filters" %>
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
                try {
                    MongoDatabase db = Conexion.getDatabase();
                    MongoCollection<Document> coleccionUsuarios = db.getCollection("usuario");

                    Document usuarioEncontrado = coleccionUsuarios.find(Filters.eq("usuario", usuario)).first();

                    if (usuarioEncontrado != null) {
                        String contrasenaBD = usuarioEncontrado.getString("contraseña");

                        if (contrasenaBD != null && contrasenaBD.equals(contrasena)) {
                            response.sendRedirect("menu.jsp");
                        } else {
                            out.println("<p style='color:red;'>Acceso denegado: contraseña incorrecta.</p>");
                        }
                    } else {
                        out.println("<p style='color:red;'>Acceso denegado: usuario no encontrado.</p>");
                    }
                } catch (Exception e) {
                    out.println("<p style='color:red;'>Error al conectar con la base de datos.</p>");
                }
            }
        }
    %>

    <p><a href="index.jsp">← Volver</a></p>
</body>
</html>