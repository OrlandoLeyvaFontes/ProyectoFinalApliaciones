<%-- 
    Document   : jugador
    Created on : 15 may 2025, 17:17:35
    Author     : Oley
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Jugador</title>
</head>
<body>
    <h1>Registrar Jugador</h1>

    <form action="RegistrarJugadorServet" method="post">
        <label>Nombre completo:</label><br>
        <input type="text" name="nombreCompleto" required><br><br>

        <label>Edad:</label><br>
        <input type="number" name="edad" required><br><br>

        <label>Posición:</label><br>
        <input type="text" name="posicion" required><br><br>

        <label>Número de camiseta:</label><br>
        <input type="number" name="numeroCamiseta" required><br><br>

        <label>Equipo (nombre exacto):</label><br>
        <input type="text" name="equipo" required><br><br>

        <input type="submit" value="Registrar">
    </form>

    <%-- Mostrar mensajes --%>
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        String error = (String) request.getAttribute("error");

        if (mensaje != null) {
    %>
        <p style="color: green;"><%= mensaje %></p>
    <%
        } else if (error != null) {
    %>
        <p style="color: red;"><%= error %></p>
    <%
        }
    %>
</body>
</html>
