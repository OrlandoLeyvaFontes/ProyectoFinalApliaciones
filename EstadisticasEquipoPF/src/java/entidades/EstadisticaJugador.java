/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Oley
 */
public class EstadisticaJugador {

    private String nombreJugador;
    private int goles;
    private int asistencias;
    private int minutosJugados;
    private boolean tarjetaAmarilla;
    private boolean tarjetaRoja;

    public EstadisticaJugador() {
    }

    public EstadisticaJugador(String nombreJugador, int goles, int asistencias, int minutosJugados, boolean tarjetaAmarilla, boolean tarjetaRoja) {
        this.nombreJugador = nombreJugador;
        this.goles = goles;
        this.asistencias = asistencias;
        this.minutosJugados = minutosJugados;
        this.tarjetaAmarilla = tarjetaAmarilla;
        this.tarjetaRoja = tarjetaRoja;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    public boolean isTarjetaAmarilla() {
        return tarjetaAmarilla;
    }

    public void setTarjetaAmarilla(boolean tarjetaAmarilla) {
        this.tarjetaAmarilla = tarjetaAmarilla;
    }

    public boolean isTarjetaRoja() {
        return tarjetaRoja;
    }

    public void setTarjetaRoja(boolean tarjetaRoja) {
        this.tarjetaRoja = tarjetaRoja;
    }

}
