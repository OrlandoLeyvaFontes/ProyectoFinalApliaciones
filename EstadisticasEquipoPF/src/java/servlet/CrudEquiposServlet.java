/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.Equipos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "CrudEquiposServlet", urlPatterns = {"/CrudEquiposServlet"})
public class CrudEquiposServlet extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        if ("editar".equalsIgnoreCase(accion)) {
            String nombre = request.getParameter("nombre");
            List<Equipos> equipos = ControlEquipos.obtenerTodos();

            Equipos equipoEditar = null;
            for (Equipos e : equipos) {
                if (e.getNombre().equalsIgnoreCase(nombre)) {
                    equipoEditar = e;
                    break;
                }
            }

            request.setAttribute("equipos", equipos);
            request.setAttribute("equipoEditar", equipoEditar);
        } else {
            request.setAttribute("equipos", ControlEquipos.obtenerTodos());
            request.setAttribute("equipoEditar", null);
        }

        request.getRequestDispatcher("Equipos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if ("agregar".equalsIgnoreCase(accion)) {
            Equipos e = new Equipos(
                request.getParameter("nombre"),
                request.getParameter("ciudad"),
                request.getParameter("estadio"),
                request.getParameter("entrenador"),
                request.getParameter("fundado"),
                request.getParameter("liga")
            );
            ControlEquipos.agregarEquipo(e);

        } else if ("eliminar".equalsIgnoreCase(accion)) {
            String nombre = request.getParameter("nombre");
            ControlEquipos.eliminarEquipo(nombre);

        } else if ("actualizar".equalsIgnoreCase(accion)) {
            String nombreOriginal = request.getParameter("nombreOriginal");
            Equipos actualizado = new Equipos(
                request.getParameter("nombre"),
                request.getParameter("ciudad"),
                request.getParameter("estadio"),
                request.getParameter("entrenador"),
                request.getParameter("fundado"),
                request.getParameter("liga")
            );
            ControlEquipos.actualizarEquipo(nombreOriginal, actualizado);
        }

        response.sendRedirect("CrudEquiposServlet");
    }}
