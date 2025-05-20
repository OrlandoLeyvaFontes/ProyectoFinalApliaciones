<%-- 
    Document   : Equipos
    Created on : 14 may 2025, 19:11:25
    Author     : Oley
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Equipos" %>
<%
    List<Equipos> listaEquipos = (List<Equipos>) request.getAttribute("equipos");
    Equipos equipoEditar = (Equipos) request.getAttribute("equipoEditar");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Equipos | Sistema Deportivo</title>
    <link rel="stylesheet" type="text/css" href="equipos.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="main-container">
        <div class="header-section">
            <h1><i class="fas fa-shield-alt"></i> Gestión de Equipos</h1>
            <p>Administración de equipos deportivos</p>
        </div>
        
        <div class="data-section">
            <div class="table-container">
                <div class="table-header">
                    <h3><i class="fas fa-list"></i> Equipos Registrados</h3>
                    <div class="search-box">
                        <i class="fas fa-search"></i>
                        <input type="text" id="searchInput" placeholder="Buscar equipo..." onkeyup="filterTable()">
                    </div>
                </div>
                
                <div class="table-responsive">
                    <table id="equiposTable">
                        <thead>
                            <tr>
                                <th><i class="fas fa-users"></i> Nombre</th>
                                <th><i class="fas fa-city"></i> Ciudad</th>
                                <th><i class="fas fa-landmark"></i> Estadio</th>
                                <th><i class="fas fa-user-tie"></i> Entrenador</th>
                                <th><i class="fas fa-calendar-alt"></i> Fundado</th>
                                <th><i class="fas fa-trophy"></i> Liga</th>
                                <th><i class="fas fa-cogs"></i> Acciones</th>
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
                                        <td class="actions-column">
                                            <form method="get" action="CrudEquiposServlet" class="action-form">
                                                <input type="hidden" name="accion" value="editar" />
                                                <input type="hidden" name="nombre" value="<%= eq.getNombre() %>" />
                                                <button class="btn btn-edit" title="Editar">
                                                    <i class="fas fa-edit"></i>
                                                </button>
                                            </form>
                                            <form method="post" action="CrudEquiposServlet" class="action-form">
                                                <input type="hidden" name="accion" value="eliminar" />
                                                <input type="hidden" name="nombre" value="<%= eq.getNombre() %>" />
                                                <button class="btn btn-delete" title="Eliminar" onclick="return confirm('¿Está seguro que desea eliminar el equipo <%= eq.getNombre() %>?');">
                                                    <i class="fas fa-trash-alt"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                            <%  } 
                            } else { %>
                                <tr class="no-data">
                                    <td colspan="7">
                                        <div class="empty-state">
                                            <i class="fas fa-info-circle"></i>
                                            <p>No hay equipos registrados en el sistema.</p>
                                        </div>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="form-container">
                <div class="form-header">
                    <h3>
                        <% if (equipoEditar != null) { %>
                            <i class="fas fa-edit"></i> Editar Equipo
                        <% } else { %>
                            <i class="fas fa-plus-circle"></i> Nuevo Equipo
                        <% } %>
                    </h3>
                </div>
                
                <form method="post" action="CrudEquiposServlet" class="equipo-form">
                    <input type="hidden" name="accion" value="<%= (equipoEditar != null) ? "actualizar" : "agregar" %>" />
                    <% if (equipoEditar != null) { %>
                        <input type="hidden" name="nombreOriginal" value="<%= equipoEditar.getNombre() %>" />
                    <% } %>
                    
                    <div class="form-group">
                        <label for="nombre"><i class="fas fa-tag"></i> Nombre:</label>
                        <input type="text" id="nombre" name="nombre" value="<%= (equipoEditar != null) ? equipoEditar.getNombre() : "" %>" required placeholder="Nombre del equipo" />
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="ciudad"><i class="fas fa-city"></i> Ciudad:</label>
                            <input type="text" id="ciudad" name="ciudad" value="<%= (equipoEditar != null) ? equipoEditar.getCiudad() : "" %>" placeholder="Ciudad del equipo" />
                        </div>
                        
                        <div class="form-group">
                            <label for="estadio"><i class="fas fa-landmark"></i> Estadio:</label>
                            <input type="text" id="estadio" name="estadio" value="<%= (equipoEditar != null) ? equipoEditar.getEstadio() : "" %>" placeholder="Nombre del estadio" />
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="entrenador"><i class="fas fa-user-tie"></i> Entrenador:</label>
                            <input type="text" id="entrenador" name="entrenador" value="<%= (equipoEditar != null) ? equipoEditar.getEntrenador() : "" %>" placeholder="Nombre del entrenador" />
                        </div>
                        
                        <div class="form-group">
                            <label for="fundado"><i class="fas fa-calendar-alt"></i> Fundado:</label>
                            <input type="text" id="fundado" name="fundado" value="<%= (equipoEditar != null) ? equipoEditar.getFundado() : "" %>" placeholder="Año de fundación" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="liga"><i class="fas fa-trophy"></i> Liga:</label>
                        <input type="text" id="liga" name="liga" value="<%= (equipoEditar != null) ? equipoEditar.getLiga() : "" %>" placeholder="Liga a la que pertenece" />
                    </div>
                    
                    <div class="button-group">
                        <button type="submit" class="btn btn-primary">
                            <% if (equipoEditar != null) { %>
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
            table = document.getElementById("equiposTable");
            tr = table.getElementsByTagName("tr");
            
            for (i = 1; i < tr.length; i++) {
                var found = false;
                for (var j = 0; j < 6; j++) { // Buscar en las 6 primeras columnas
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
