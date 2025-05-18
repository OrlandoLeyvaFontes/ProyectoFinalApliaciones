/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opciones;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import conexion.Conexion;
import entidades.Equipos;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Oley
 */
public class ControlEquipos {
    private static MongoCollection<Equipos> coleccion;

    static {
        MongoDatabase db = Conexion.getDatabase();
        coleccion = db.getCollection("equipo", Equipos.class); 
    }


public static List<Equipos> obtenerTodos() {
    System.out.println("*** INICIANDO obtenerTodos EN ControlEquipos ***");
    List<Equipos> equipos = new ArrayList<>();
    
    try {
        System.out.println("Obteniendo colección de MongoDB");
        coleccion.find().into(equipos);
        System.out.println("Consulta MongoDB completada. Número de equipos: " + equipos.size());
        
        if (!equipos.isEmpty()) {
            Equipos primero = equipos.get(0);
            System.out.println("Primer equipo: " + primero.getNombre() + ", " + primero.getCiudad());
        } else {
            System.out.println("No se encontraron equipos en la colección");
        }
    } catch (Exception e) {
        System.err.println("ERROR al obtener equipos de MongoDB: " + e.getMessage());
        e.printStackTrace();
    }
    
    return equipos;
}
    public static void agregarEquipo(Equipos equipo) {
        coleccion.insertOne(equipo); 
    }

    public static void eliminarEquipo(String nombreEquipo) {
        coleccion.deleteOne(Filters.eq("nombre", nombreEquipo)); 
    }

    public static void actualizarEquipo(String nombreEquipoOriginal, Equipos nuevo) {
        coleccion.updateOne(
            Filters.eq("nombre", nombreEquipoOriginal),
            Updates.combine(
                Updates.set("nombre", nuevo.getNombre()),
                Updates.set("ciudad", nuevo.getCiudad()),
                Updates.set("estadio", nuevo.getEstadio()),
                Updates.set("entrenador", nuevo.getEntrenador()),
                Updates.set("fundado", nuevo.getFundado()),
                Updates.set("liga", nuevo.getLiga())
            ) 
        );
    }
}
