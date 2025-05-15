<%@page import="opciones.Usuario"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Usuarios</title>
    <style>
        .oculto {
            display: none;
        }
    </style>
    <script>
        function mostrarTabla() {
            document.getElementById("tablaUsuarios").classList.remove("oculto");
            document.getElementById("btnVerMas").style.display = "none";
        }
    </script>
</head>
<body>
    <h1>Usuarios</h1>

    <form method="post" action="CrudUsuariosServlet">
        <input type="hidden" name="accion" value="agregar" />
        Usuario: <input type="text" name="usuario" required><br>
        Nombre: <input type="text" name="nombre" required><br>
        Correo: <input type="email" name="correo" required><br>
        Contraseña: <input type="password" name="contraseña" required><br>
        Edad: <input type="number" name="edad" required><br>
        <button type="submit">Agregar</button>
    </form>

    <br>

    <button id="btnVerMas" onclick="mostrarTabla()">Ver tabla</button>

    <table border="1" id="tablaUsuarios" class="oculto">
        <tr>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Edad</th>
            <th>Acciones</th>
        </tr>
        <%
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            if (usuarios != null && !usuarios.isEmpty()) {
                for (Usuario u : usuarios) {
        %>
        <tr>
            <td><%= u.getUsuario() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getCorreo() %></td>
            <td><%= u.getEdad() %></td>
            <td>
                <form action="CrudUsuariosServlet" method="post" style="display:inline;">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="usuario" value="<%= u.getUsuario() %>">
                    <button type="submit">Eliminar</button>
                </form>
                <form action="CrudUsuariosServlet" method="get" style="display:inline;">
                    <input type="hidden" name="accion" value="editar">
                    <input type="hidden" name="usuario" value="<%= u.getUsuario() %>">
                    <button type="submit">Editar</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No hay usuarios registrados aún.</td>
        </tr>
        <% } %>
    </table>
</body>
</html>
