/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;



import entidades.Equipos;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import opciones.ControlEquipos;

/**
 *
 * @author Oley
 */
@WebServlet(name = "EquiposServlet", urlPatterns = {"/EquiposServlet"})
public class EquiposServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String accion = request.getParameter("accion");
        
        try {
            switch (accion) {
                case "agregar":
                    agregarEquipo(request, response);
                    break;
                case "eliminar":
                    eliminarEquipo(request, response);
                    break;
                case "actualizar":
                    actualizarEquipo(request, response);
                    break;
                default:
                    response.sendRedirect("equipos.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("equipos.jsp?error=" + e.getMessage());
        }
    }

    private void agregarEquipo(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        Equipos equipo = new Equipos();
        equipo.setNombre(request.getParameter("nombre"));
        equipo.setCiudad(request.getParameter("ciudad"));
        equipo.setEstadio(request.getParameter("estadio"));
        equipo.setEntrenador(request.getParameter("entrenador"));
        equipo.setFundado(request.getParameter("fundado"));
        equipo.setLiga(request.getParameter("liga"));
        
        ControlEquipos.agregarEquipo(equipo);
        response.sendRedirect("equipos.jsp");
    }

    private void eliminarEquipo(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String nombre = request.getParameter("nombre");
        ControlEquipos.eliminarEquipo(nombre);
        response.sendRedirect("equipos.jsp");
    }

    private void actualizarEquipo(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String nombreOriginal = request.getParameter("originalNombre");
        
        Equipos equipo = new Equipos();
        equipo.setNombre(request.getParameter("nombre"));
        equipo.setCiudad(request.getParameter("ciudad"));
        equipo.setEstadio(request.getParameter("estadio"));
        equipo.setEntrenador(request.getParameter("entrenador"));
        equipo.setFundado(request.getParameter("fundado"));
        equipo.setLiga(request.getParameter("liga"));
        
        ControlEquipos.actualizarEquipo(nombreOriginal, equipo);
        response.sendRedirect("equipos.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
