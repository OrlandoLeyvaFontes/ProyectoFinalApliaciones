/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.Jugador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlJugador;

/**
 *
 * @author Oley
 */
@WebServlet(name = "RegistrarJugadorServet", urlPatterns = {"/RegistrarJugadorServet"})
public class RegistrarJugadorServet extends HttpServlet {
   private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreCompleto = request.getParameter("nombreCompleto");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String posicion = request.getParameter("posicion");
        int numeroCamiseta = Integer.parseInt(request.getParameter("numeroCamiseta"));
        String equipoNombre = request.getParameter("equipo");

        Jugador jugador = new Jugador();
        jugador.setNombreCompleto(nombreCompleto);
        jugador.setEdad(edad);
        jugador.setPosicion(posicion);
        jugador.setNumeroCamiseta(numeroCamiseta);

        ControlJugador control = new ControlJugador();
        boolean registrado = control.registrarJugador(jugador, equipoNombre); 

        if (registrado) {
            request.setAttribute("mensaje", "Jugador registrado correctamente.");
        } else {
            request.setAttribute("error", "No se encontró el equipo '" + equipoNombre + "'.");
        }

        request.getRequestDispatcher("jugador.jsp").forward(request, response);
    }
    
}
