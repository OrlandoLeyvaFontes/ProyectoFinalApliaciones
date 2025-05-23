package servlet;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import entidades.Equipos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlEquipos;
import org.bson.Document;

@WebServlet(name = "ValidarNombreServlet", urlPatterns = {"/ValidarNombreServlet"})
public class ValidarNombreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");

        try (PrintWriter out = response.getWriter()) {
            // Validación inicial
            if (nombre == null || tipo == null || nombre.trim().isEmpty() || tipo.trim().isEmpty()) {
                out.write("{\"error\":\"❌ Parámetros incompletos.\"}");
                return;
            }

            nombre = nombre.trim();
            tipo = tipo.trim().toLowerCase();
            boolean existe = false;

            if ("equipo".equals(tipo)) {
                for (Equipos e : ControlEquipos.obtenerTodos()) {
                    if (e.getNombre().equalsIgnoreCase(nombre)) {
                        existe = true;
                        break;
                    }
                }
            } else if ("jugador".equals(tipo)) {
                MongoCollection<Document> col = Conexion.getDatabase().getCollection("jugadores");
                Document jugador = col.find(Filters.eq("nombreCompleto", nombre)).first();
                existe = (jugador != null);
            } else {
                out.write("{\"error\":\"❌ Tipo inválido. Usa 'equipo' o 'jugador'.\"}");
                return;
            }

            out.write("{\"existe\":" + existe + "}");
        } catch (Exception e) {
            response.getWriter().write("{\"error\":\"❌ Error interno: " + e.getMessage().replace("\"", "'") + "\"}");
        }
    }
}
