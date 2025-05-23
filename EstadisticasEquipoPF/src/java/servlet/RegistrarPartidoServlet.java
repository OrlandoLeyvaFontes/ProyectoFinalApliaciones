package servlet;

import entidades.EstadisticaJugador;
import entidades.Partido;
import java.io.IOException;
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

import conexion.Conexion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;

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

        // Validaciones para obligar
        if (equipo == null || rival == null || lugar == null || resultado == null ||
            equipo.isEmpty() || rival.isEmpty() || lugar.isEmpty() || resultado.isEmpty()) {
            request.setAttribute("mensaje", " Todos los campos del partido son obligatorios.");
            request.getRequestDispatcher("partido.jsp").forward(request, response);
            return;
        }

        // Valida la fecha
        Date fecha = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha = sdf.parse(fechaStr);
        } catch (Exception e) {
            request.setAttribute("mensaje", " Error al convertir la fecha.");
            request.getRequestDispatcher("partido.jsp").forward(request, response);
            return;
        }

        // Valida si ya existe partido del mismo equipo contra mismo rival en la misma fecha
        MongoDatabase db = Conexion.getDatabase();
        MongoCollection<Document> partidos = db.getCollection("partidos");
        Document partidoExistente = partidos.find(and(
            eq("equipo", equipo),
            eq("rival", rival),
            eq("fecha", fecha)
        )).first();

        if (partidoExistente != null) {
            request.setAttribute("mensaje", " Ya existe un partido registrado para ese equipo contra ese rival en esa fecha.");
            request.getRequestDispatcher("partido.jsp").forward(request, response);
            return;
        }

        // Datos de jugadores
        String[] nombres = request.getParameterValues("nombreJugador");
        String[] goles = request.getParameterValues("goles");
        String[] asistencias = request.getParameterValues("asistencias");
        String[] minutos = request.getParameterValues("minutosJugados");
        String[] amarillas = request.getParameterValues("tarjetaAmarilla");
        String[] rojas = request.getParameterValues("tarjetaRoja");

        List<EstadisticaJugador> estadisticas = new ArrayList<>();

        if (nombres != null) {
            for (int i = 0; i < nombres.length; i++) {
                String nombreJugador = nombres[i];
                if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
                    request.setAttribute("mensaje", " Todos los jugadores deben tener nombre.");
                    request.getRequestDispatcher("partido.jsp").forward(request, response);
                    return;
                }

                try {
                    int g = Integer.parseInt(goles[i]);
                    int a = Integer.parseInt(asistencias[i]);
                    int m = Integer.parseInt(minutos[i]);

                    if (g < 0 || a < 0 || m < 0) {
                        throw new NumberFormatException("Valores negativos.");
                    }

                    EstadisticaJugador e = new EstadisticaJugador();
                    e.setNombreJugador(nombreJugador);
                    e.setGoles(g);
                    e.setAsistencias(a);
                    e.setMinutosJugados(m);
                    e.setTarjetaAmarilla(amarillas != null && amarillas.length > i && amarillas[i].equals("on"));
                    e.setTarjetaRoja(rojas != null && rojas.length > i && rojas[i].equals("on"));
                    estadisticas.add(e);
                } catch (NumberFormatException ex) {
                    request.setAttribute("mensaje", " Las estadísticas de los jugadores deben ser números válidos y positivos.");
                    request.getRequestDispatcher("partido.jsp").forward(request, response);
                    return;
                }
            }
        }

        Partido partido = new Partido(fecha, rival, lugar, resultado, estadisticas);

        try {
            ControlPartido control = new ControlPartido();
            control.registrarPartido(partido, equipo);
            request.setAttribute("mensaje", " Partido registrado correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("mensaje", " Error al registrar partido: " + ex.getMessage());
        }

        request.getRequestDispatcher("partido.jsp").forward(request, response);
    }
}
