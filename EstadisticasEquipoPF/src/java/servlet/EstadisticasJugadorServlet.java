/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import entidades.EstadisticaJugador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlEstadisticaJugador;

/**
 *
 * @author Oley
 */
@WebServlet(name = "EstadisticasJugadorServlet", urlPatterns = {"/EstadisticasJugadorServlet"})
public class EstadisticasJugadorServlet extends HttpServlet {
private ControlEstadisticaJugador control;

    @Override
    public void init() throws ServletException {
        control = new ControlEstadisticaJugador();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Verificar conexión a MongoDB
            long documentCount = control.contarDocumentos();
            System.out.println("Documentos en la colección 'partidos': " + documentCount);

            // Obtener estadísticas
            List<EstadisticaJugador> lista = control.obtenerTodasEstadisticas();
            System.out.println("Estadísticas recuperadas: " + (lista != null ? lista.size() : "null"));

            req.setAttribute("estadisticas", lista);
            req.getRequestDispatcher("/estadisticas.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al cargar estadísticas: " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
