/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.Equipos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlEquipos;

/**
 *
 * @author Oley
 */
@WebServlet(name = "RegistrarEquiposServlet", urlPatterns = {"/RegistrarEquiposServlet"})
public class RegistrarEquiposServlet extends HttpServlet {

      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String ciudad = request.getParameter("ciudad");
        String estadio = request.getParameter("estadio");
        String entrenador = request.getParameter("entrenador");
        String fundado = request.getParameter("fundado");
        String liga = request.getParameter("liga");

        Equipos equipo = new Equipos(nombre, ciudad, estadio, entrenador, fundado, liga);

        try {
            ControlEquipos.agregarEquipo(equipo);
            request.setAttribute("mensaje", "Equipo registrado correctamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al registrar el equipo: " + e.getMessage());
        }

        request.getRequestDispatcher("Equipos.jsp").forward(request, response);
    }
}
