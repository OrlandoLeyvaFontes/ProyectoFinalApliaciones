/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.Conexion;
import entidades.EstadisticaJugador;
import entidades.Partido;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Oley
 */
public class ControlPartido {
      private MongoCollection<Document> coleccion;

    public ControlPartido() {
        MongoDatabase db = Conexion.getDatabase();
        coleccion = db.getCollection("partidos");
    }

    public void registrarPartido(Partido p, String equipoNombre) {
        List<Document> estadisticasDocs = new ArrayList<>();
        for (EstadisticaJugador e : p.getEstadisticas()) {
            estadisticasDocs.add(new Document("nombreJugador", e.getNombreJugador())
                    .append("goles", e.getGoles())
                    .append("asistencias", e.getAsistencias())
                    .append("minutosJugados", e.getMinutosJugados())
                    .append("tarjetaAmarilla", e.isTarjetaAmarilla())
                    .append("tarjetaRoja", e.isTarjetaRoja()));
        }

        Document doc = new Document("equipo", equipoNombre)
                .append("fecha", p.getFecha())
                .append("rival", p.getRival())
                .append("lugar", p.getLugar())
                .append("resultado", p.getResultado())
                .append("estadisticas", estadisticasDocs);

        coleccion.insertOne(doc);
    }
}
