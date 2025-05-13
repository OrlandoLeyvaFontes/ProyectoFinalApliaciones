<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="opciones.Usuario"%>
<%@page import="opciones.ControlUsuario"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Usuarios Registrados</title></head>
<body>
    <h2>Lista de Usuarios</h2>

    <%
        List<Usuario> lista = ControlUsuario.obtenerTodos();

        String usuarioEditar = request.getParameter("editar");
        Usuario usuarioEditado = null;
        if (usuarioEditar != null) {
            for (Usuario u : lista) {
                if (u.getUsuario().equals(usuarioEditar)) {
                    usuarioEditado = u;
                    break;
                }
            }
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String accion = request.getParameter("accion");

            if ("Agregar".equals(accion)) {
                String usuario = request.getParameter("usuario");
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");
                int edad = Integer.parseInt(request.getParameter("edad"));

                int id = lista.size() + 1;
                Usuario nuevoUsuario = new Usuario(id, usuario, correo, contrasena, nombre, edad);
                ControlUsuario.agregarUsuario(nuevoUsuario);
                usuarioEditado = null;

            } else if ("Eliminar".equals(accion)) {
                String usuarioEliminar = request.getParameter("usuario");
                ControlUsuario.eliminarUsuario(usuarioEliminar);
                usuarioEditado = null;

            } else if ("Actualizar".equals(accion)) {
                String usuario = request.getParameter("usuario");
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");
                int edad = Integer.parseInt(request.getParameter("edad"));
                String usuarioOriginal = request.getParameter("usuarioOriginal");

                Usuario usuarioActualizado = new Usuario(0, usuario, correo, contrasena, nombre, edad);
                ControlUsuario.actualizarUsuario(usuarioOriginal, usuarioActualizado);
                usuarioEditado = null;
            }

            lista = ControlUsuario.obtenerTodos(); 
        }
    %>

    <form method="post">
        Usuario: <input type="text" name="usuario" value="<%= usuarioEditado != null ? usuarioEditado.getUsuario() : "" %>" <%= usuarioEditado != null ? "readonly" : "required" %>><br>
        Nombre: <input type="text" name="nombre" value="<%= usuarioEditado != null ? usuarioEditado.getNombre() : "" %>" required><br>
        Correo: <input type="text" name="correo" value="<%= usuarioEditado != null ? usuarioEditado.getCorreo() : "" %>" required><br>
        Contraseña: <input type="text" name="contrasena" value="<%= usuarioEditado != null ? usuarioEditado.getContraseña(): "" %>" required><br>
        Edad: <input type="number" name="edad" value="<%= usuarioEditado != null ? usuarioEditado.getEdad() : "" %>" required><br>
        <input type="hidden" name="usuarioOriginal" value="<%= usuarioEditado != null ? usuarioEditado.getUsuario() : "" %>">
        <input type="submit" name="accion" value="<%= usuarioEditado != null ? "Actualizar" : "Agregar" %>">
    </form>

    <br>

    <table border="1">
        <tr><th>Usuario</th><th>Nombre</th><th>Correo</th><th>Contraseña</th><th>Edad</th><th>Acciones</th></tr>
        <%
            if (lista.isEmpty()) {
        %>
            <tr><td colspan="6">No hay usuarios registrados.</td></tr>
        <%
            } else {
                for (Usuario u : lista) {
        %>
            <tr>
                <td><%= u.getUsuario() %></td>
                <td><%= u.getNombre() %></td>
                <td><%= u.getCorreo() %></td>
                <td><%= u.getContraseña()%></td>
                <td><%= u.getEdad() %></td>
                <td>
                    <form method="post" style="display:inline;">
                        <input type="hidden" name="usuario" value="<%= u.getUsuario() %>">
                        <input type="submit" name="accion" value="Eliminar">
                    </form>
                    <form method="get" style="display:inline;">
                        <input type="hidden" name="editar" value="<%= u.getUsuario() %>">
                        <input type="submit" value="Editar">
                    </form>
                </td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
