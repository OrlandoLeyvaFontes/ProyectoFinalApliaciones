/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.EstadisticaJugador;
import entidades.Partido;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlPartido;

/**
 *
 * @author Oley
 */
@WebServlet(name = "RegistrarPartidoServlet", urlPatterns = {"/RegistrarPartidoServlet"})
public class RegistrarPartidoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String equipo = request.getParameter("equipo");
        String fechaStr = request.getParameter("fecha");
        String rival = request.getParameter("rival");
        String lugar = request.getParameter("lugar");
        String resultado = request.getParameter("resultado");

        Date fecha = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha = sdf.parse(fechaStr);
        } catch (Exception e) {
            request.setAttribute("mensaje", "❌ Error al convertir la fecha.");
            request.getRequestDispatcher("partido.jsp").forward(request, response);
            return;
        }

        String[] nombres = request.getParameterValues("nombreJugador");
        String[] goles = request.getParameterValues("goles");
        String[] asistencias = request.getParameterValues("asistencias");
        String[] minutos = request.getParameterValues("minutosJugados");
        String[] amarillas = request.getParameterValues("tarjetaAmarilla");
        String[] rojas = request.getParameterValues("tarjetaRoja");

        List<EstadisticaJugador> estadisticas = new ArrayList<>();

        if (nombres != null) {
            for (int i = 0; i < nombres.length; i++) {
                EstadisticaJugador e = new EstadisticaJugador();
                e.setNombreJugador(nombres[i]);
                e.setGoles(Integer.parseInt(goles[i]));
                e.setAsistencias(Integer.parseInt(asistencias[i]));
                e.setMinutosJugados(Integer.parseInt(minutos[i]));
                e.setTarjetaAmarilla(amarillas != null && amarillas.length > i && amarillas[i].equals("on"));
                e.setTarjetaRoja(rojas != null && rojas.length > i && rojas[i].equals("on"));
                estadisticas.add(e);
            }
        }

        Partido partido = new Partido(fecha, rival, lugar, resultado, estadisticas);

        ControlPartido control = new ControlPartido();
        control.registrarPartido(partido, equipo);

        request.setAttribute("mensaje", "✅ Partido registrado correctamente.");
        request.getRequestDispatcher("partido.jsp").forward(request, response);
    }
    
}
