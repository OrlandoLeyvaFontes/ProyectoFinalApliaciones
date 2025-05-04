/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;

/**
 *
 * @author Oley
 */
public class Usuario {
    int id;
    String usuario;
    String correo;
    String contraseña;
    String nombre;
    int edad;
     String tipo;

    public Usuario(int id, String usuario, String correo, String contraseña, String nombre, int edad, String tipo) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

 public Usuario(String usuario, String nombre, String correo) {
    this.usuario = usuario;
    this.nombre = nombre;
    this.correo = correo;
}


    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
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
    
    
}
