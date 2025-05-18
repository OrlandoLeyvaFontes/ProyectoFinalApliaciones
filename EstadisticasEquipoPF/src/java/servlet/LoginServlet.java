/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author Oley
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

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

                if (contrasena.equals(passBD)) {
                    response.sendRedirect("menu.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=contrasena");
                }
            } else {
                response.sendRedirect("login.jsp?error=usuario");
            }

        } catch (Exception e) {
            response.sendRedirect("login.jsp?error=conexion");
        }
    }
   
}
