/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlUsuario;
import opciones.Usuario;

/**
 *
 * @author Oley
 */
@WebServlet(name = "CrudUsuariosServlet", urlPatterns = {"/CrudUsuariosServlet"})
public class CrudUsuariosServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        if ("editar".equals(accion)) {
            String usuario = request.getParameter("usuario");
            List<Usuario> usuarios = ControlUsuario.obtenerTodos();

            Usuario usuarioEditar = null;
            for (Usuario u : usuarios) {
                if (u.getUsuario().equals(usuario)) {
                    usuarioEditar = u;
                    break;
                }
            }

            request.setAttribute("usuarios", usuarios);
            request.setAttribute("usuarioEditar", usuarioEditar);
        } else {
            List<Usuario> usuarios = ControlUsuario.obtenerTodos();
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("usuarioEditar", null);
        }

        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("agregar".equals(accion)) {
            Usuario u = new Usuario();
            u.setUsuario(request.getParameter("usuario"));
            u.setNombre(request.getParameter("nombre"));
            u.setCorreo(request.getParameter("correo"));
            u.setContraseña(request.getParameter("contraseña"));
            u.setEdad(Integer.parseInt(request.getParameter("edad")));

            ControlUsuario.agregarUsuario(u);

        } else if ("eliminar".equals(accion)) {
            String usuario = request.getParameter("usuario");
            ControlUsuario.eliminarUsuario(usuario);

        } else if ("actualizar".equals(accion)) {
            String usuarioOriginal = request.getParameter("usuarioOriginal");
            Usuario u = new Usuario();
            u.setUsuario(request.getParameter("usuario"));
            u.setNombre(request.getParameter("nombre"));
            u.setCorreo(request.getParameter("correo"));
            u.setContraseña(request.getParameter("contraseña"));
            u.setEdad(Integer.parseInt(request.getParameter("edad")));

            ControlUsuario.actualizarUsuario(usuarioOriginal, u);
        }

        response.sendRedirect("CrudUsuariosServlet");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para CRUD de usuarios";
    }
}