/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RegistroUsuarioServlet", urlPatterns = {"/RegistroUsuarioServlet"})
public class RegistroUsuarioServlet extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");
        int edad = 0;

        try {
            edad = Integer.parseInt(request.getParameter("edad"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "La edad debe ser un número válido.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        if (usuario.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || nombre.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContraseña(contrasena);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEdad(edad);

        try {
            ControlUsuario.agregarUsuario(nuevoUsuario);
            request.setAttribute("mensaje", "Usuario registrado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar usuario: " + e.getMessage());
        }

        request.getRequestDispatcher("registro.jsp").forward(request, response);
    }
}
