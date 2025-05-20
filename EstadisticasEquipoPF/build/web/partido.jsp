<%-- 
    Document   : partido
    Created on : 15 may 2025, 18:41:20
    Author     : Oley
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Partidos | Sistema Deportivo</title>
    <link rel="stylesheet" href="partido.css"> <!-- Reutilizando el mismo CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="main-container">
        <div class="header-section">
            <h1><i class="fas fa-futbol"></i> Registrar Partido</h1>
            <p>Registro de encuentros deportivos y estadísticas</p>
        </div>

        <div class="form-container">
            <div class="form-header">
                <h3><i class="fas fa-plus-circle"></i> Nuevo Partido</h3>
            </div>

            <form action="RegistrarPartidoServlet" method="post" class="equipo-form">
                <div class="form-group">
                    <label for="equipo"><i class="fas fa-users"></i> Nombre del Equipo:</label>
                    <input type="text" id="equipo" name="equipo" required placeholder="Equipo local">
                </div>

                <div class="form-group">
                    <label for="rival"><i class="fas fa-user-friends"></i> Nombre del Rival:</label>
                    <input type="text" id="rival" name="rival" required placeholder="Equipo visitante">
                </div>

                <div class="form-group">
                    <label for="fecha"><i class="fas fa-calendar-alt"></i> Fecha (yyyy-mm-dd):</label>
                    <input type="date" id="fecha" name="fecha" required>
                </div>

                <div class="form-group">
                    <label for="lugar"><i class="fas fa-map-marker-alt"></i> Lugar:</label>
                    <input type="text" id="lugar" name="lugar" required placeholder="Ej: Estadio Azteca">
                </div>

                <div class="form-group">
                    <label for="resultado"><i class="fas fa-clipboard-check"></i> Resultado (ej. 2-1):</label>
                    <input type="text" id="resultado" name="resultado" required>
                </div>

                <div class="form-header">
                    <h3><i class="fas fa-chart-bar"></i> Estadísticas por Jugador</h3>
                </div>

                <div class="form-group">
                    <label for="nombreJugador"><i class="fas fa-user"></i> Nombre del Jugador:</label>
                    <input type="text" id="nombreJugador" name="nombreJugador" required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="goles"><i class="fas fa-futbol"></i> Goles:</label>
                        <input type="number" id="goles" name="goles" min="0" required>
                    </div>

                    <div class="form-group">
                        <label for="asistencias"><i class="fas fa-handshake"></i> Asistencias:</label>
                        <input type="number" id="asistencias" name="asistencias" min="0" required>
                    </div>

                    <div class="form-group">
                        <label for="minutosJugados"><i class="fas fa-clock"></i> Minutos Jugados:</label>
                        <input type="number" id="minutosJugados" name="minutosJugados" min="0" required>
                    </div>
                </div>

                <div class="form-group">
                    <label><input type="checkbox" name="tarjetaAmarilla"> Tarjeta Amarilla</label>
                    <label><input type="checkbox" name="tarjetaRoja"> Tarjeta Roja</label>
                </div>

                <input class="btn" type="submit" value="Registrar Partido">
            </form>

            <form action="menuAdministrador.jsp" method="get">
                <input class="btn back-btn" type="submit" value="Volver al Menú">
            </form>
        </div>
    </div>
</body>
</html>
