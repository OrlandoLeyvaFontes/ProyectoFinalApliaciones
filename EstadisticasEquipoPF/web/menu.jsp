<%-- 
    Document   : menu
    Created on : 13 may 2025, 01:21:14
    Author     : Oley
--%>

<%@page import="opciones.ControlEquipos"%>
<%@page import="entidades.Equipos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Menú de Equipos</title>
</head>
<body>
    <h1>Equipos de la Liga MX</h1>

    <%
        String accion = request.getParameter("accion");

        if ("agregar".equals(accion)) {
            String nombre = request.getParameter("nombre");
            String ciudad = request.getParameter("ciudad");
            String estadio = request.getParameter("estadio");
            String entrenador = request.getParameter("entrenador");
            int fundado = Integer.parseInt(request.getParameter("fundado"));
            String liga = request.getParameter("liga");

            Equipos nuevoEquipo = new Equipos(nombre, ciudad, estadio, entrenador, fundado, liga);
            ControlEquipos.agregarEquipo(nuevoEquipo);
            response.sendRedirect(request.getRequestURI());
            return;
        }

        if ("eliminar".equals(accion)) {
            String nombreEquipo = request.getParameter("nombre");
            ControlEquipos.eliminarEquipo(nombreEquipo);
            response.sendRedirect(request.getRequestURI());
            return;
        }

        if ("actualizar".equals(accion)) {
            String originalNombre = request.getParameter("originalNombre");
            String nombre = request.getParameter("nombre");
            String ciudad = request.getParameter("ciudad");
            String estadio = request.getParameter("estadio");
            String entrenador = request.getParameter("entrenador");
            int fundado = Integer.parseInt(request.getParameter("fundado"));
            String liga = request.getParameter("liga");

            Equipos equipoActualizado = new Equipos(nombre, ciudad, estadio, entrenador, fundado, liga);
            ControlEquipos.actualizarEquipo(originalNombre, equipoActualizado);
            response.sendRedirect(request.getRequestURI());
            return;
        }
    %>

    <h2>Equipos</h2>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Ciudad</th>
            <th>Estadio</th>
            <th>Entrenador</th>
            <th>Año de Fundación</th>
            <th>Liga</th>
            <th>Acciones</th>
        </tr>
        <%
            List<Equipos> equipos = ControlEquipos.obtenerTodos();
            for (Equipos equipo : equipos) {
        %>
        <tr>
            <td><%= equipo.getNombre() %></td>
            <td><%= equipo.getCiudad() %></td>
            <td><%= equipo.getEstadio() %></td>
            <td><%= equipo.getEntrenador() %></td>
            <td><%= equipo.getFundado() %></td>
            <td><%= equipo.getLiga() %></td>
            <td>
                <form action="" method="post" style="display:inline;">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="nombre" value="<%= equipo.getNombre() %>">
                    <button type="submit">Eliminar</button>
                </form>
                &nbsp;
                <button onclick="mostrarFormularioActualizacion('<%= equipo.getNombre() %>')">Editar</button>
                <div id="formActualizar_<%= equipo.getNombre() %>" style="display:none; margin-top:5px;">
                    <form action="" method="post">
                        <input type="hidden" name="accion" value="actualizar">
                        <input type="hidden" name="originalNombre" value="<%= equipo.getNombre() %>">
                        Nombre: <input type="text" name="nombre" value="<%= equipo.getNombre() %>" size="10"><br>
                        Ciudad: <input type="text" name="ciudad" value="<%= equipo.getCiudad() %>" size="10"><br>
                        Estadio: <input type="text" name="estadio" value="<%= equipo.getEstadio() %>" size="10"><br>
                        Entrenador: <input type="text" name="entrenador" value="<%= equipo.getEntrenador() %>" size="10"><br>
                        Fundado: <input type="number" name="fundado" value="<%= equipo.getFundado() %>" size="4"><br>
                        Liga: <input type="text" name="liga" value="<%= equipo.getLiga() %>" size="2"><br>
                        <button type="submit">Actualizar</button>
                    </form>
                </div>
            </td>
        </tr>
        <% } %>
    </table>

    <h2>Agregar Nuevo Equipo</h2>
    <form action="" method="post">
        <input type="hidden" name="accion" value="agregar">
        <label>Nombre: <input type="text" name="nombre" required></label><br>
        <label>Ciudad: <input type="text" name="ciudad" required></label><br>
        <label>Estadio: <input type="text" name="estadio" required></label><br>
        <label>Entrenador: <input type="text" name="entrenador" required></label><br>
        <label>Año de Fundación: <input type="number" name="fundado" required></label><br>
        <label>Liga: <input type="text" name="liga" required></label><br>
        <button type="submit">Agregar</button>
    </form>

    <script>
        function mostrarFormularioActualizacion(nombre) {
            var formulario = document.getElementById("formActualizar_" + nombre);
            if (formulario.style.display === "none") {
                formulario.style.display = "block";
            } else {
                formulario.style.display = "none";
            }
        }
    </script>
</body>
</html>