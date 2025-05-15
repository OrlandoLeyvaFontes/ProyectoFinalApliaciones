/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Oley
 */
public class Equipos {

    private String nombre;
    private String ciudad;
    private String estadio;
    private String entrenador;
    private String fundado;
    private String liga;

    public Equipos(String nombre, String ciudad, String estadio, String entrenador, String fundado, String liga) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.entrenador = entrenador;
        this.fundado = fundado;
        this.liga = liga;
    }

    public Equipos() {
    }

    public Equipos(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public String getFundado() {
        return fundado;
    }

    public void setFundado(String fundado) {
        this.fundado = fundado;
    }

   

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    


}
