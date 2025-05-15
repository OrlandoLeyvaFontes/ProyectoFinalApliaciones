/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.Conexion;
import entidades.Jugador;
import org.bson.Document;
/**
 *
 * @author Oley
 */
public class ControlJugador {
     private MongoCollection<Document> coleccion;

    public ControlJugador() {
        MongoDatabase db = Conexion.getDatabase();
        coleccion = db.getCollection("jugadores");
    }

    public void registrarJugador(Jugador j, String equipoNombre) {
        Document doc = new Document("equipo", equipoNombre)
                .append("nombreCompleto", j.getNombreCompleto())
                .append("edad", j.getEdad())
                .append("posicion", j.getPosicion())
                .append("numeroCamiseta", j.getNumeroCamiseta());
        coleccion.insertOne(doc);
    }
}
