/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.Conexion;
import entidades.Equipos;
import org.bson.Document;

/**
 *
 * @author Oley
 */
public class MainPruebas {
    public static void main(String[] args) {
        
    try {
        MongoDatabase db = Conexion.getDatabase();
        System.out.println("Conexión a MongoDB establecida");
        
        for (String name : db.listCollectionNames()) {
            System.out.println("Colección encontrada: " + name);
        }
        
        MongoCollection<Document> rawCollection = db.getCollection("equipo");
        long count = rawCollection.countDocuments();
        System.out.println("Documentos en colección 'equipo': " + count);
        
        if (count > 0) {
            Document doc = rawCollection.find().first();
            System.out.println("Ejemplo de documento: " + doc.toJson());
        }
    } catch (Exception e) {
        System.err.println("ERROR en testMongoDB: " + e.getMessage());
        e.printStackTrace();
    }

}
}