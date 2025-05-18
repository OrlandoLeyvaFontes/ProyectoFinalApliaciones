/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import entidades.Equipos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlEquipos;
import org.bson.Document;

/**
 *
 * @author Oley
 */
@WebServlet(name = "ValidarNombreServlet", urlPatterns = {"/ValidarNombreServlet"})
public class ValidarNombreServlet extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");

        boolean existe = false;
        if ("equipo".equalsIgnoreCase(tipo)) {
            for (Equipos e : ControlEquipos.obtenerTodos()) {
                if (e.getNombre().equalsIgnoreCase(nombre)) {
                    existe = true;
                    break;
                }
            }
        } else if ("jugador".equalsIgnoreCase(tipo)) {
            MongoCollection<Document> col = Conexion.getDatabase().getCollection("jugadores");
            Document jugador = col.find(Filters.eq("nombreCompleto", nombre)).first();
            existe = (jugador != null);
        }

        response.setContentType("application/json");
        response.getWriter().write("{\"existe\":" + existe + "}");
    }
}
