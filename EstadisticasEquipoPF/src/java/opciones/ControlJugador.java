/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import conexion.Conexion;
import entidades.Jugador;
import org.bson.Document;
/**
 *
 * @author Oley
 */
public class ControlJugador {
      private MongoCollection<Document> coleccionJugadores;
    private MongoCollection<Document> coleccionEquipos;

    public ControlJugador() {
        MongoDatabase db = Conexion.getDatabase();
        coleccionJugadores = db.getCollection("jugadores");
        coleccionEquipos = db.getCollection("equipo");
    }

    public boolean registrarJugador(Jugador j, String equipoNombre) {
        Document equipoEncontrado = coleccionEquipos.find(Filters.eq("nombre", equipoNombre)).first();

        if (equipoEncontrado == null) {
            return false; 
        }

        Document doc = new Document("equipo", equipoNombre)
                .append("nombreCompleto", j.getNombreCompleto())
                .append("edad", j.getEdad())
                .append("posicion", j.getPosicion())
                .append("numeroCamiseta", j.getNumeroCamiseta());

        coleccionJugadores.insertOne(doc);
        return true; 
    }
    
}
