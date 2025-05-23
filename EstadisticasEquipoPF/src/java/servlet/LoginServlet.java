package servlet;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");  // Soporte para caracteres especiales

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        // Validar campos vacíos
        if (usuario == null || usuario.trim().isEmpty() ||
            contrasena == null || contrasena.trim().isEmpty()) {
            response.sendRedirect("login.jsp?error=camposVacios");
            return;
        }

        usuario = usuario.trim();
        contrasena = contrasena.trim();

        // Validación para administrador
        if ("admin".equals(usuario) && "admin".equals(contrasena)) {
            response.sendRedirect("menuAdministrador.jsp");
            return;
        }

        try {
            MongoDatabase db = Conexion.getDatabase();
            MongoCollection<Document> usuarios = db.getCollection("usuario");

            Document encontrado = usuarios.find(Filters.eq("usuario", usuario)).first();

            if (encontrado != null) {
                String passBD = encontrado.getString("contraseña");

                if (passBD != null && contrasena.equals(passBD)) {
                    response.sendRedirect("menu.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=contrasenaIncorrecta");
                }
            } else {
                response.sendRedirect("login.jsp?error=usuarioNoEncontrado");
            }

        } catch (Exception e) {
            // Se puede guardar el error en logs del servidor para depuración
            response.sendRedirect("login.jsp?error=errorServidor");
        }
    }
}
