<%-- 
    Document   : estadisticas
    Created on : 15 may 2025, 20:50:40
    Author     : Oley
--%>

<%@page import="opciones.ControlEstadisticaJugador"%>
<%@ page import="java.util.List" %>
<%@ page import="entidades.EstadisticaJugador" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ControlEstadisticaJugador control = new ControlEstadisticaJugador();
    List<EstadisticaJugador> lista = control.obtenerTodasEstadisticas();
%>

<html>
<head>
    <title>Estadísticas de Jugadores</title>
</head>
<body>
    <h1>Estadísticas de Jugadores</h1>

    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Goles</th>
                <th>Asistencias</th>
                <th>Minutos Jugados</th>
                <th>Tarjeta Amarilla</th>
                <th>Tarjeta Roja</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (lista != null && !lista.isEmpty()) {
                    for (EstadisticaJugador e : lista) {
            %>
                <tr>
                    <td><%= e.getNombreJugador() %></td>
                    <td><%= e.getGoles() %></td>
                    <td><%= e.getAsistencias() %></td>
                    <td><%= e.getMinutosJugados() %></td>
                    <td><%= e.isTarjetaAmarilla() ? "Sí" : "No" %></td>
                    <td><%= e.isTarjetaRoja() ? "Sí" : "No" %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="6">No hay estadísticas para mostrar.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
