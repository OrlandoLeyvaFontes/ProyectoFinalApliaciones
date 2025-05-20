<%-- 
    Document   : jugador
    Created on : 15 may 2025, 17:17:35
    Author     : Oley
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Jugador | Sistema Deportivo</title>
    <link rel="stylesheet" href="jugador.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="form-header">
            <h1><i class="fas fa-user-plus"></i> Registrar Jugador</h1>
        </div>
        
        <form action="RegistrarJugadorServet" method="post" class="registro-form">
            <div class="form-group">
                <label for="nombreCompleto"><i class="fas fa-user"></i> Nombre completo:</label>
                <input type="text" id="nombreCompleto" name="nombreCompleto" required placeholder="Ingrese nombre completo">
            </div>
            
            <div class="form-row">
                <div class="form-group half">
                    <label for="edad"><i class="fas fa-birthday-cake"></i> Edad:</label>
                    <input type="number" id="edad" name="edad" required placeholder="Edad" min="15" max="50">
                </div>
                
                <div class="form-group half">
                    <label for="numeroCamiseta"><i class="fas fa-tshirt"></i> Número:</label>
                    <input type="number" id="numeroCamiseta" name="numeroCamiseta" required placeholder="Nº" min="1" max="99">
                </div>
            </div>
            
            <div class="form-group">
                <label for="posicion"><i class="fas fa-running"></i> Posición:</label>
                <input type="text" id="posicion" name="posicion" required placeholder="Ej: Delantero, Defensa...">
            </div>
            
            <div class="form-group">
                <label for="equipo"><i class="fas fa-users"></i> Equipo (nombre exacto):</label>
                <input type="text" id="equipo" name="equipo" required placeholder="Ingrese nombre del equipo">
            </div>
            
            <div class="actions">
                <button type="submit" class="btn submit-btn">
                    <i class="fas fa-save"></i> Registrar Jugador
                </button>
                
                <a href="menuAdministrador.jsp" class="btn back-btn">
                    <i class="fas fa-arrow-left"></i> Volver al Menú
                </a>
            </div>
        </form>
        
        <%-- Mostrar mensajes --%>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            String error = (String) request.getAttribute("error");
            if (mensaje != null) {
        %>
            <div class="mensaje-container success">
                <i class="fas fa-check-circle"></i>
                <p><%= mensaje %></p>
            </div>
        <%
            } else if (error != null) {
        %>
            <div class="mensaje-container error">
                <i class="fas fa-exclamation-circle"></i>
                <p><%= error %></p>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>