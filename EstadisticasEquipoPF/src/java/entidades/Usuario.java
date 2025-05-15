/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;

import org.bson.types.ObjectId;

/**
 *
 * @author Oley
 */
public class Usuario {
    String usuario;
    String correo;
    String contraseña;
    String nombre;
    int edad;

    public Usuario(String usuario, String correo, String contraseña, String nombre, int edad) {
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.edad = edad;
    }

  
    
 



    public Usuario() {
    }

  

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

  
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   
    
}
