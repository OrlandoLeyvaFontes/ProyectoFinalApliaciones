<%-- 
    Document   : menuAdministrador
    Created on : 15 may 2025, 19:49:01
    Author     : Oley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel Administrador | Sistema Deportivo</title>
    <link rel="stylesheet" type="text/css" href="menuAdministrador.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1><i class="fas fa-shield-alt"></i> Panel de Administración</h1>
            <p>Sistema de Gestión Deportiva</p>
        </div>
        <div class="menu-container">
            <div class="menu-item">
                <a href="usuarios.jsp" class="menu-button">
                    <div class="icon"><i class="fas fa-users"></i></div>
                    <div class="text">
                        <h3>Gestión de Usuarios</h3>
                        <p>Administrar cuentas y permisos</p>
                    </div>
                </a>
            </div>
            <div class="menu-item">
                <a href="Equipos.jsp" class="menu-button">
                    <div class="icon"><i class="fas fa-football-ball"></i></div>
                    <div class="text">
                        <h3>Registrar Equipos</h3>
                        <p>Crear y modificar equipos</p>
                    </div>
                </a>
            </div>
            <div class="menu-item">
                <a href="jugador.jsp" class="menu-button">
                    <div class="icon"><i class="fas fa-running"></i></div>
                    <div class="text">
                        <h3>Registrar Jugadores</h3>
                        <p>Gestionar información de jugadores</p>
                    </div>
                </a>
            </div>
            <div class="menu-item">
                <a href="partido.jsp" class="menu-button">
                    <div class="icon"><i class="fas fa-calendar-alt"></i></div>
                    <div class="text">
                        <h3>Registrar Partidos</h3>
                        <p>Programar y actualizar eventos</p>
                    </div>
                </a>
            </div>
        </div>
        <div class="footer">
            <p>© 2025 Sistema de Gestión Deportiva</p>
        </div>
    </div>
</body>
</html>
