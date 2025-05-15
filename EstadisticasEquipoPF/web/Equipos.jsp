<%-- 
    Document   : Equipos
    Created on : 14 may 2025, 19:11:25
    Author     : Oley
--%>

<%@ page import="java.util.List" %>
<%@ page import="entidades.Equipos" %>
<%
    List<Equipos> listaEquipos = (List<Equipos>) request.getAttribute("equipos");
    Equipos equipoEditar = (Equipos) request.getAttribute("equipoEditar");
%>

<h2>Equipos</h2>

<table border="1" cellpadding="5">
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Ciudad</th>
            <th>Estadio</th>
            <th>Entrenador</th>
            <th>Fundado</th>
            <th>Liga</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
    <% if (listaEquipos != null && !listaEquipos.isEmpty()) {
        for (Equipos eq : listaEquipos) { %>
        <tr>
            <td><%= eq.getNombre() %></td>
            <td><%= eq.getCiudad() %></td>
            <td><%= eq.getEstadio() %></td>
            <td><%= eq.getEntrenador() %></td>
            <td><%= eq.getFundado() %></td>
            <td><%= eq.getLiga() %></td>
            <td>
                <form method="get" action="CrudEquiposServlet" style="display:inline;">
                    <input type="hidden" name="accion" value="editar" />
                    <input type="hidden" name="nombre" value="<%= eq.getNombre() %>" />
                    <button type="submit">Editar</button>
                </form>
                <form method="post" action="CrudEquiposServlet" style="display:inline;">
                    <input type="hidden" name="accion" value="eliminar" />
                    <input type="hidden" name="nombre" value="<%= eq.getNombre() %>" />
                    <button type="submit" onclick="return confirm('¿Seguro que quieres eliminar este equipo?');">Eliminar</button>
                </form>
            </td>
        </tr>
    <%  } 
    } else { %>
        <tr><td colspan="7">No hay equipos</td></tr>
    <% } %>
    </tbody>
</table>

<h3><%= (equipoEditar != null) ? "Editar Equipo" : "Agregar Equipo" %></h3>
<form method="post" action="CrudEquiposServlet">
    <input type="hidden" name="accion" value="<%= (equipoEditar != null) ? "actualizar" : "agregar" %>" />
    <% if (equipoEditar != null) { %>
        <input type="hidden" name="nombreOriginal" value="<%= equipoEditar.getNombre() %>" />
    <% } %>

    Nombre: <input type="text" name="nombre" value="<%= (equipoEditar != null) ? equipoEditar.getNombre() : "" %>" required /><br />
    Ciudad: <input type="text" name="ciudad" value="<%= (equipoEditar != null) ? equipoEditar.getCiudad() : "" %>" /><br />
    Estadio: <input type="text" name="estadio" value="<%= (equipoEditar != null) ? equipoEditar.getEstadio() : "" %>" /><br />
    Entrenador: <input type="text" name="entrenador" value="<%= (equipoEditar != null) ? equipoEditar.getEntrenador() : "" %>" /><br />
    Fundado: <input type="text" name="fundado" value="<%= (equipoEditar != null) ? equipoEditar.getFundado() : "" %>" /><br />
    Liga: <input type="text" name="liga" value="<%= (equipoEditar != null) ? equipoEditar.getLiga() : "" %>" /><br />

    <button type="submit"><%= (equipoEditar != null) ? "Actualizar" : "Agregar" %></button>
</form>
