<%@page import="opciones.Usuario"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios | Sistema Deportivo</title>
    <link rel="stylesheet" type="text/css" href="usuarios.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="main-container">
        <div class="header-section">
            <h1><i class="fas fa-users"></i> Gestión de Usuarios</h1>
            <p>Administración de usuarios del sistema</p>
        </div>
    <div class="data-section">
        <div class="table-container">
            <div class="table-header">
                <h3><i class="fas fa-list"></i> Usuarios Registrados</h3>
                <div class="search-box">
                    <i class="fas fa-search"></i>
                    <input type="text" id="searchInput" placeholder="Buscar usuario..." onkeyup="filterTable()">
                </div>
            </div>
            
            <div class="table-responsive">
                <table id="usuariosTable">
                    <thead>
                        <tr>
                            <th><i class="fas fa-user"></i> Usuario</th>
                            <th><i class="fas fa-id-card"></i> Nombre</th>
                            <th><i class="fas fa-envelope"></i> Correo</th>
                            <th><i class="fas fa-birthday-cake"></i> Edad</th>
                            <th><i class="fas fa-cogs"></i> Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
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
                                <td class="actions-column">
                                    <form method="get" action="CrudUsuariosServlet" class="action-form">
                                        <input type="hidden" name="accion" value="editar" />
                                        <input type="hidden" name="usuario" value="<%= u.getUsuario() %>" />
                                        <button class="btn btn-edit" title="Editar">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                    </form>
                                    <form method="post" action="CrudUsuariosServlet" class="action-form">
                                        <input type="hidden" name="accion" value="eliminar" />
                                        <input type="hidden" name="usuario" value="<%= u.getUsuario() %>" />
                                        <button class="btn btn-delete" title="Eliminar" onclick="return confirm('¿Está seguro que desea eliminar el usuario <%= u.getUsuario() %>?');">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        <% 
                                }
                            } else { 
                        %>
                            <tr class="no-data">
                                <td colspan="5">
                                    <div class="empty-state">
                                        <i class="fas fa-info-circle"></i>
                                        <p>No hay usuarios registrados en el sistema.</p>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="form-container">
            <% 
                Usuario usuarioEditar = (Usuario) request.getAttribute("usuarioEditar");
                boolean enEdicion = usuarioEditar != null;
            %>
            <div class="form-header">
                <h3>
                    <% if (enEdicion) { %>
                        <i class="fas fa-edit"></i> Editar Usuario
                    <% } else { %>
                        <i class="fas fa-plus-circle"></i> Nuevo Usuario
                    <% } %>
                </h3>
            </div>
            
            <form method="post" action="CrudUsuariosServlet" class="usuario-form">
                <input type="hidden" name="accion" value="<%= enEdicion ? "actualizar" : "agregar" %>" />
                <% if (enEdicion) { %>
                    <input type="hidden" name="usuarioOriginal" value="<%= usuarioEditar.getUsuario() %>" />
                <% } %>
                
                <div class="form-group">
                    <label for="usuario"><i class="fas fa-user"></i> Usuario:</label>
                    <input type="text" id="usuario" name="usuario" value="<%= enEdicion ? usuarioEditar.getUsuario() : "" %>" required <%= enEdicion ? "readonly" : "" %> placeholder="Nombre de usuario" />
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="nombre"><i class="fas fa-id-card"></i> Nombre:</label>
                        <input type="text" id="nombre" name="nombre" value="<%= enEdicion ? usuarioEditar.getNombre() : "" %>" required placeholder="Nombre completo" />
                    </div>
                    
                    <div class="form-group">
                        <label for="edad"><i class="fas fa-birthday-cake"></i> Edad:</label>
                        <input type="number" id="edad" name="edad" value="<%= enEdicion ? usuarioEditar.getEdad() : "" %>" required placeholder="Edad" />
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="correo"><i class="fas fa-envelope"></i> Correo:</label>
                    <input type="email" id="correo" name="correo" value="<%= enEdicion ? usuarioEditar.getCorreo() : "" %>" required placeholder="Correo electrónico" />
                </div>
                
                <div class="form-group">
                    <label for="contraseña"><i class="fas fa-lock"></i> <%= enEdicion ? "Contraseña (opcional)" : "Contraseña" %>:</label>
                    <input type="password" id="contraseña" name="contraseña" <%= !enEdicion ? "required" : "" %> placeholder="Contraseña" />
                </div>
                
                <div class="button-group">
                    <button type="submit" class="btn btn-primary">
                        <% if (enEdicion) { %>
                            <i class="fas fa-save"></i> Actualizar
                        <% } else { %>
                            <i class="fas fa-plus"></i> Agregar
                        <% } %>
                    </button>
                    
                    <a href="menuAdministrador.jsp" class="btn btn-secondary">
                        <i class="fas fa-arrow-left"></i> Volver al Menú
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function filterTable() {
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("usuariosTable");
        tr = table.getElementsByTagName("tr");
        
        for (i = 1; i < tr.length; i++) {
            var found = false;
            for (var j = 0; j < 4; j++) { // Buscar en las 4 primeras columnas
                td = tr[i].getElementsByTagName("td")[j];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
</script>
</body>
</html>
