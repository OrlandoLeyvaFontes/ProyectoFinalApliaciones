/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.Conexion;
import entidades.Equipos;
import entidades.EstadisticaJugador;
import java.util.List;
import opciones.ControlEstadisticaJugador;
import org.bson.Document;

/**
 *
 * @author Oley
 */
public class MainPruebas {
//    public static void main(String[] args) {
        
//    try {
//        MongoDatabase db = Conexion.getDatabase();
//        System.out.println("Conexión a MongoDB establecida");
//        
//        for (String name : db.listCollectionNames()) {
//            System.out.println("Colección encontrada: " + name);
//        }
//        
//        MongoCollection<Document> rawCollection = db.getCollection("equipo");
//        long count = rawCollection.countDocuments();
//        System.out.println("Documentos en colección 'equipo': " + count);
//        
//        if (count > 0) {
//            Document doc = rawCollection.find().first();
//            System.out.println("Ejemplo de documento: " + doc.toJson());
//        }
//    } catch (Exception e) {
//        System.err.println("ERROR en testMongoDB: " + e.getMessage());
//        e.printStackTrace();
//    }

//      ControlEstadisticaJugador dao = new ControlEstadisticaJugador();
//
//
//      // Listar equipos para saber cuál usar
//        dao.listarEquipos();
//
//        // Obtener y mostrar todas las estadísticas
//        List<EstadisticaJugador> todas = dao.obtenerTodasEstadisticas();
//        for (EstadisticaJugador ej : todas) {
//            System.out.println("Jugador: " + ej.getNombreJugador() +
//                    ", Goles: " + ej.getGoles() +
//                    ", Asistencias: " + ej.getAsistencias() +
//                    ", Minutos: " + ej.getMinutosJugados() +
//                    ", Amarilla: " + ej.isTarjetaAmarilla() +
//                    ", Roja: " + ej.isTarjetaRoja());
//        }
//
//        // Obtener estadísticas de un equipo que ya exista (cambia el nombre)
//        String equipo = "NombreExactoDelEquipoQueSalióEnLaLista";
//        List<EstadisticaJugador> estadisticasEquipo = dao.obtenerEstadisticasPorEquipo(equipo);
//        System.out.println("\nEstadísticas para equipo: " + equipo);
//        for (EstadisticaJugador ej : estadisticasEquipo) {
//            System.out.println("Jugador: " + ej.getNombreJugador() +
//                    ", Goles: " + ej.getGoles() +
//                    ", Asistencias: " + ej.getAsistencias() +
//                    ", Minutos: " + ej.getMinutosJugados() +
//                    ", Amarilla: " + ej.isTarjetaAmarilla() +
//                    ", Roja: " + ej.isTarjetaRoja());
//        }
//    }
      
      
//      ControlEstadisticaJugador control = new ControlEstadisticaJugador();
//
//        System.out.println("Estadísticas ordenadas por goles (mayor a menor):\n");
//
//        for (EstadisticaJugador ej : control.obtenerEstadisticasOrdenadasDesc()) {
//            System.out.println("Nombre: " + ej.getNombreJugador());
//            System.out.println("Goles: " + ej.getGoles());
//            System.out.println("Asistencias: " + ej.getAsistencias());
//            System.out.println("Minutos Jugados: " + ej.getMinutosJugados());
//            System.out.println("Tarjeta Amarilla: " + (ej.isTarjetaAmarilla() ? "Sí" : "No"));
//            System.out.println("Tarjeta Roja: " + (ej.isTarjetaRoja() ? "Sí" : "No"));
//            System.out.println("----------------------------------------");
//        }
    
  

    public static void main(String[] args) {
        System.out.println("🚀 Probando lógica del servlet...");
        
        ControlEstadisticaJugador control = new ControlEstadisticaJugador();
        List<EstadisticaJugador> stats = control.obtenerTodasEstadisticas();
        
        // Ordenar por goles (descendente)
        stats.sort((a, b) -> Integer.compare(b.getGoles(), a.getGoles()));
        
        System.out.println("\n📊 Resultados ordenados:");
        System.out.println("-----------------------");
        for (EstadisticaJugador jugador : stats) {
            System.out.printf("%s: %d goles%n", 
                jugador.getNombreJugador(), 
                jugador.getGoles());
        }
    
    
}
}