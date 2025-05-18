<%-- 
    Document   : partido
    Created on : 15 may 2025, 18:41:20
    Author     : Oley
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Partido</title>
</head>
<body>
    <h1>Registrar Partido</h1>
    <form action="RegistrarPartidoServlet" method="post">
        <label for="equipo">Nombre del Equipo:</label>
        <input type="text" name="equipo" required><br><br>

        <label for="fecha">Fecha (yyyy-mm-dd):</label>
        <input type="date" name="fecha" required><br><br>

        <label for="rival">Nombre del Rival:</label>
        <input type="text" name="rival" required><br><br>

        <label for="lugar">Lugar:</label>
        <input type="text" name="lugar" required><br><br>

        <label for="resultado">Resultado (ej. 2-1):</label>
        <input type="text" name="resultado" required><br><br>

        <h3>Estadísticas por Jugador</h3>
        <div>
            <label>Nombre del Jugador:</label>
            <input type="text" name="nombreJugador" required><br>

            <label>Goles:</label>
            <input type="number" name="goles" min="0" required><br>

            <label>Asistencias:</label>
            <input type="number" name="asistencias" min="0" required><br>

            <label>Minutos Jugados:</label>
            <input type="number" name="minutosJugados" min="0" required><br>

            <label>Tarjeta Amarilla:</label>
            <input type="checkbox" name="tarjetaAmarilla"><br>

            <label>Tarjeta Roja:</label>
            <input type="checkbox" name="tarjetaRoja"><br>
        </div>


        <input type="submit" value="Registrar Partido">
    </form>
</body>
</html>
