/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import entidades.EstadisticaJugador;
import entidades.Jugador;
import entidades.Partido;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlEstadisticaJugador;
import org.bson.Document;

/**
 *
 * @author Oley
 */
@WebServlet(name = "EstadisticasOrdenadasServlet", urlPatterns = {"/EstadisticasOrdenadasServlet"})
public class EstadisticasOrdenadasServlet extends HttpServlet {
    
private ControlEstadisticaJugador control;

    @Override
    public void init() throws ServletException {
        control = new ControlEstadisticaJugador();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        try {
            System.out.println("[DEBUG] Iniciando obtención de estadísticas...");
            long totalDocumentos = control.contarDocumentos();
            System.out.println("[DEBUG] Documentos en colección: " + totalDocumentos);

            if(totalDocumentos == 0) {
                System.out.println("[WARN] La colección está vacía");
                req.setAttribute("aviso", "No hay datos disponibles en la base de datos");
            }

            List<EstadisticaJugador> estadisticas = control.obtenerTodasEstadisticas();
            
            if(estadisticas == null) {
                throw new Exception("El método obtenerTodasEstadisticas() retornó null");
            }

            estadisticas.sort((a, b) -> Integer.compare(b.getGoles(), a.getGoles()));
            
            System.out.println("[DEBUG] Total estadísticas obtenidas: " + estadisticas.size());
            if(!estadisticas.isEmpty()) {
                System.out.println("[DEBUG] Top 3 jugadores:");
                for(int i = 0; i < Math.min(3, estadisticas.size()); i++) {
                    EstadisticaJugador j = estadisticas.get(i);
                    System.out.printf("- %s: %d goles%n", j.getNombreJugador(), j.getGoles());
                }
            }

            req.setAttribute("estadisticas", estadisticas);
            
            System.out.println("[DEBUG] Redirigiendo a EstadisticasMayor.jsp");
            req.getRequestDispatcher("EstadisticasMayor.jsp").forward(req, resp);
            
        } catch (Exception e) {
            System.err.println("[ERROR] Error en EstadisticasOrdenadasServlet:");
            e.printStackTrace();
            
            req.setAttribute("error", "Error al generar estadísticas: " + e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }


}

