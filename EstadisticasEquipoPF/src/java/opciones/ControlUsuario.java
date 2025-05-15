/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.Conexion;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

/**
 *
 * @author Oley
 */
public class ControlUsuario {
    private static MongoCollection<Usuario> coleccion;

    static {
        MongoDatabase db = Conexion.getDatabase();
        coleccion = db.getCollection("usuario", Usuario.class);
    }

   public static List<Usuario> obtenerTodos() {
    List<Usuario> usuarios = new ArrayList<>();
    coleccion.find().into(usuarios);
    System.out.println("Usuarios: " + usuarios.size()); 
    return usuarios;
}


   public static void agregarUsuario(Usuario usuario) {
    System.out.println("Agregando usuario: " + usuario.getUsuario());
    System.out.println("Contraseña: " + usuario.getContraseña()); 
    coleccion.insertOne(usuario);
}


    public static void eliminarUsuario(String usuario) {
        coleccion.deleteOne(Filters.eq("usuario", usuario));
    }

  public static void actualizarUsuario(String usuarioOriginal, Usuario nuevo) {
    coleccion.updateOne(
        Filters.eq("usuario", usuarioOriginal),
        Updates.combine(
            Updates.set("usuario", nuevo.getUsuario()),
            Updates.set("correo", nuevo.getCorreo()),
            Updates.set("contraseña", nuevo.getContraseña()),
            Updates.set("nombre", nuevo.getNombre()),
            Updates.set("edad", nuevo.getEdad())
        )
    );
}

        

   
}
