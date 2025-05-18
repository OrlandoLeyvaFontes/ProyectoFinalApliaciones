/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Oley
 */
public class Partido {

    private Date fecha;
    private String rival;
    private String lugar;
    private String resultado; 
    private List<EstadisticaJugador> estadisticas;

    public Partido() {
    }

    public Partido(Date fecha, String rival, String lugar, String resultado, List<EstadisticaJugador> estadisticas) {
        this.fecha = fecha;
        this.rival = rival;
        this.lugar = lugar;
        this.resultado = resultado;
        this.estadisticas = estadisticas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getRival() {
        return rival;
    }

    public void setRival(String rival) {
        this.rival = rival;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List<EstadisticaJugador> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(List<EstadisticaJugador> estadisticas) {
        this.estadisticas = estadisticas;
    }
}
