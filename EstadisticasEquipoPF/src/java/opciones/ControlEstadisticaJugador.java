/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import entidades.EstadisticaJugador;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Oley
 */
public class ControlEstadisticaJugador {

   private MongoCollection<Document> coleccionPartidos;

    public ControlEstadisticaJugador() {
        MongoDatabase db = Conexion.getDatabase();
        coleccionPartidos = db.getCollection("partidos");
    }

    public long contarDocumentos() {
        return coleccionPartidos.countDocuments();
    }

    public List<String> obtenerEquipos() {
        return coleccionPartidos.distinct("equipo", String.class).into(new ArrayList<>());
    }

    public List<EstadisticaJugador> obtenerTodasEstadisticas() {
        List<EstadisticaJugador> lista = new ArrayList<>();

        FindIterable<Document> partidos = coleccionPartidos.find();

        for (Document partido : partidos) {
            List<Document> estadisticasDocs = (List<Document>) partido.get("estadisticas");

            if (estadisticasDocs != null) {
                for (Document e : estadisticasDocs) {
                    EstadisticaJugador ej = new EstadisticaJugador();
                    ej.setNombreJugador(e.getString("nombreJugador"));
                    ej.setGoles(e.getInteger("goles", 0));
                    ej.setAsistencias(e.getInteger("asistencias", 0));
                    ej.setMinutosJugados(e.getInteger("minutosJugados", 0));
                    ej.setTarjetaAmarilla(e.getBoolean("tarjetaAmarilla", false));
                    ej.setTarjetaRoja(e.getBoolean("tarjetaRoja", false));
                    lista.add(ej);
                }
            }
        }
        System.out.println("Estadísticas encontradas: " + lista.size());
        return lista;
    }

    public List<EstadisticaJugador> obtenerEstadisticasPorEquipo(String equipoNombre) {
        List<EstadisticaJugador> lista = new ArrayList<>();

        FindIterable<Document> partidos = coleccionPartidos.find(Filters.eq("equipo", equipoNombre));

        for (Document partido : partidos) {
            List<Document> estadisticasDocs = (List<Document>) partido.get("estadisticas");

            if (estadisticasDocs != null) {
                for (Document e : estadisticasDocs) {
                    EstadisticaJugador ej = new EstadisticaJugador();
                    ej.setNombreJugador(e.getString("nombreJugador"));
                    ej.setGoles(e.getInteger("goles", 0));
                    ej.setAsistencias(e.getInteger("asistencias", 0));
                    ej.setMinutosJugados(e.getInteger("minutosJugados", 0));
                    ej.setTarjetaAmarilla(e.getBoolean("tarjetaAmarilla", false));
                    ej.setTarjetaRoja(e.getBoolean("tarjetaRoja", false));
                    lista.add(ej);
                }
            }
        }
        return lista;
    }
public List<EstadisticaJugador> obtenerEstadisticasOrdenadasPorGoles() {
    List<EstadisticaJugador> lista = obtenerTodasEstadisticas();
    lista.sort((a, b) -> Integer.compare(b.getGoles(), a.getGoles()));
    return lista;
}


}
