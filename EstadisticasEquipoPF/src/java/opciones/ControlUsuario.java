/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oley
 */
public class ControlUsuario {
        private static List<Usuario> listaUsuarios=new ArrayList<>();
    static{ 
//        listaUsuarios.add(new Usuario( 1, "Rodrigo123", "Rodrigo@yopmail.com", "Rodrigo123", "Rodrigo",18));
        
    }
    public static List<Usuario> obtenerLista(){
        return listaUsuarios;
    }
    public static void agregarUsuarios(Usuario usuario){
        listaUsuarios.add(usuario);
    }
     public static Usuario buscarUsuario(String usuario, String contrasena) {
        for (Usuario u : listaUsuarios) {
            if (u.getUsuario().equals(usuario) && u.getContraseña().equals(contrasena)) {
                return u;
            }
        }
        return null;
    }
     public static void eliminarUsuario(String usuario) {
    listaUsuarios.removeIf(u -> u.getUsuario().equals(usuario));
}

     
//    public static void main(String[] args) {
//        Usuario usuario=new Usuario( 1, "Rodrigo123", "Rodrigo@yopmail.com", "Rodrigo123", "Rodrigo",18,"admin");
//        agregarUsuarios(usuario);
//        Usuario usuario1=new Usuario(2, "CarlosFreeFire", "Carlos@gmail.com", "Carlos123", "Carlos ", 21,"usuario");
//        agregarUsuarios(usuario1);
//        for (Usuario u: obtenerLista()) {
//            System.out.println(u.getUsuario()+" "+ u.getContraseña());
//        }
//        
//    }
}
