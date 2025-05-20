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

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Estadísticas de Jugadores</title>
    <link rel="stylesheet" href="estadisticas.css">
</head>
<body>

<div class="contenedor-estadisticas">
    <h1>Estadísticas de Jugadores</h1>

    <div class="boton-regresar">
        <form action="menu.jsp" method="get">
            <button type="submit">← Regresar al Menú</button>
        </form>
    </div>

    <table class="tabla-estadisticas">
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
                    <td colspan="6" class="sin-datos">No hay estadísticas para mostrar.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

</body>
</html>
