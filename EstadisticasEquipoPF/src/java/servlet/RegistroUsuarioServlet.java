package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opciones.ControlUsuario;
import opciones.Usuario;
import conexion.Conexion;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

import java.util.regex.Pattern;

/**
 *
 * @author Oley
 */
@WebServlet(name = "RegistroUsuarioServlet", urlPatterns = {"/RegistroUsuarioServlet"})
public class RegistroUsuarioServlet extends HttpServlet {

    private boolean esCorreoValido(String correo) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, correo);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String nombre = request.getParameter("nombre");
        int edad = 0;

        // Validar campos vacios
        if (usuario == null || correo == null || contrasena == null || nombre == null ||
            usuario.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || nombre.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        // Validar edad
        try {
            edad = Integer.parseInt(request.getParameter("edad"));
            if (edad < 18 || edad > 45) {
                request.setAttribute("error", "La edad debe estar entre 18 y 45.");
                request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "La edad debe ser un número válido.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        // Validar correo
        if (!esCorreoValido(correo)) {
            request.setAttribute("error", "El correo electrónico no es válido.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        // Validar contraseña
        if (contrasena.length() < 6) {
            request.setAttribute("error", "La contraseña debe tener al menos 6 caracteres.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        // Verificar si el usuario o correo ya existen en MongoDB
        MongoDatabase database = Conexion.getDatabase();
        MongoCollection<Document> coleccionUsuarios = database.getCollection("usuarios");

        if (coleccionUsuarios.find(eq("usuario", usuario)).first() != null) {
            request.setAttribute("error", "El nombre de usuario ya está registrado.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        if (coleccionUsuarios.find(eq("correo", correo)).first() != null) {
            request.setAttribute("error", "El correo electrónico ya está registrado.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
            return;
        }

        // Crear y registrar usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContraseña(contrasena);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEdad(edad);

        try {
            ControlUsuario.agregarUsuario(nuevoUsuario); // Este método debe guardar el usuario
            request.setAttribute("mensaje", "Usuario registrado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar usuario: " + e.getMessage());
        }

        request.getRequestDispatcher("registro.jsp").forward(request, response);
    }
}
