package servlet;

import entidades.Jugador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlJugador;

@WebServlet(name = "RegistrarJugadorServet", urlPatterns = {"/RegistrarJugadorServet"})
public class RegistrarJugadorServet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreCompleto = request.getParameter("nombreCompleto");
        String edadStr = request.getParameter("edad");
        String posicion = request.getParameter("posicion");
        String numeroCamisetaStr = request.getParameter("numeroCamiseta");
        String equipoNombre = request.getParameter("equipo");

        // Validaciones
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()
                || edadStr == null || edadStr.trim().isEmpty()
                || posicion == null || posicion.trim().isEmpty()
                || numeroCamisetaStr == null || numeroCamisetaStr.trim().isEmpty()
                || equipoNombre == null || equipoNombre.trim().isEmpty()) {

            request.setAttribute("error", " Todos los campos son obligatorios.");
            request.getRequestDispatcher("jugador.jsp").forward(request, response);
            return;
        }

        nombreCompleto = nombreCompleto.trim();
        posicion = posicion.trim();
        equipoNombre = equipoNombre.trim();

        int edad;
        int numeroCamiseta;
        try {
            edad = Integer.parseInt(edadStr);
            numeroCamiseta = Integer.parseInt(numeroCamisetaStr);

            if (edad < 10 || edad > 60) {
                request.setAttribute("error", " La edad debe estar entre 10 y 60 años.");
                request.getRequestDispatcher("jugador.jsp").forward(request, response);
                return;
            }

            if (numeroCamiseta < 0 || numeroCamiseta > 99) {
                request.setAttribute("error", " El número de camiseta debe estar entre 0 y 99.");
                request.getRequestDispatcher("jugador.jsp").forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", " Edad y número de camiseta deben ser números válidos.");
            request.getRequestDispatcher("jugador.jsp").forward(request, response);
            return;
        }

        Jugador jugador = new Jugador();
        jugador.setNombreCompleto(nombreCompleto);
        jugador.setEdad(edad);
        jugador.setPosicion(posicion);
        jugador.setNumeroCamiseta(numeroCamiseta);

        ControlJugador control = new ControlJugador();
        boolean registrado = control.registrarJugador(jugador, equipoNombre); 

        if (registrado) {
            request.setAttribute("mensaje", " Jugador registrado correctamente.");
        } else {
            request.setAttribute("error", " No se encontró el equipo '" + equipoNombre + "'.");
        }

        request.getRequestDispatcher("jugador.jsp").forward(request, response);
    }
}
