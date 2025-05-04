<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="opciones.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Usuarios Registrados</title></head>
<body>
    <h2>Lista de Usuarios</h2>

    <%
        List<Usuario> lista = (List<Usuario>) session.getAttribute("usuarios");
        if (lista == null) {
            lista = new ArrayList<>();
            session.setAttribute("usuarios", lista);
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String accion = request.getParameter("accion");

            if ("Agregar".equals(accion)) {
                String usuario = request.getParameter("usuario");
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");

                lista.add(new Usuario(usuario, nombre, correo));
            } else if ("Eliminar".equals(accion)) {
                String usuarioEliminar = request.getParameter("usuario");
                lista.removeIf(u -> u.getUsuario().equals(usuarioEliminar));
            } else if ("Actualizar".equals(accion)) {
                String usuario = request.getParameter("usuario");
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");

                for (Usuario u : lista) {
                    if (u.getUsuario().equals(usuario)) {
                        u.setNombre(nombre);
                        u.setCorreo(correo);
                        break;
                    }
                }
            }
        }

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
    %>

    <form method="post">
        Usuario: <input type="text" name="usuario" value="<%= usuarioEditado != null ? usuarioEditado.getUsuario() : "" %>" <%= usuarioEditado != null ? "readonly" : "required" %>>
        Nombre: <input type="text" name="nombre" value="<%= usuarioEditado != null ? usuarioEditado.getNombre() : "" %>" required>
        Correo: <input type="text" name="correo" value="<%= usuarioEditado != null ? usuarioEditado.getCorreo() : "" %>" required>
        <input type="submit" name="accion" value="<%= usuarioEditado != null ? "Actualizar" : "Agregar" %>">
    </form>

    <br>

    <table border="1">
        <tr><th>Usuario</th><th>Nombre</th><th>Correo</th><th>Acciones</th></tr>
        <%
            for (Usuario u : lista) {
        %>
            <tr>
                <td><%= u.getUsuario() %></td>
                <td><%= u.getNombre() %></td>
                <td><%= u.getCorreo() %></td>
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
        %>
    </table>
</body>
</html>