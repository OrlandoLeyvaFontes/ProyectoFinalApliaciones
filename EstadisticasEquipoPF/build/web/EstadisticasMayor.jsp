<%-- 
    Document   : EstadisticasMayor
    Created on : 17 may 2025, 12:35:31
    Author     : Oley
--%>

<%@page import="entidades.EstadisticaJugador"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
List<EstadisticaJugador> estadisticas = (List<EstadisticaJugador>) request.getAttribute("estadisticas");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Goles de Jugadores</title>
</head>
<body>
    <h2>Información de Depuración</h2>
    <div style="background: #f0f0f0; padding: 10px;">
        <strong>Estado:</strong><br>
        - Request URI: <%= request.getRequestURI() %><br>
        - Atributo 'estadisticas': <%= estadisticas != null ? "EXISTE ("+estadisticas.size()+")" : "NULL" %><br>
        - Método HTTP: <%= request.getMethod() %>
    </div>

    <h1>Goles de Jugadores</h1>
    <% if (estadisticas == null) { %>
        <p style="color: red;">ERROR: El atributo 'estadisticas' es null</p>
    <% } else if (estadisticas.isEmpty()) { %>
        <p>No hay datos disponibles en la base de datos</p>
    <% } else { %>
        <table border="1">
            <tr>
                <th>Jugador</th>
                <th>Goles</th>
            </tr>
            <% for (EstadisticaJugador jugador : estadisticas) { %>
            <tr>
                <td><%= jugador.getNombreJugador() %></td>
                <td><%= jugador.getGoles() %></td>
            </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>