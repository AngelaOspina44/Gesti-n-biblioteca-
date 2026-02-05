package dao;

import bibliotecabd.ConexionMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import model.Suscriptor;
import org.bson.Document;

public class SuscriptorDAO {

    private final MongoCollection<Document> coleccion;

    public SuscriptorDAO() {
    this(ConexionMongo.getDatabase());
    }

    public SuscriptorDAO(MongoDatabase database) {
        this.coleccion = database.getCollection("suscriptores");
    }

    
    // Listar todos como objetos Suscriptor
    public List<Suscriptor> obtenerTodos() {
        List<Suscriptor> lista = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Suscriptor s = new Suscriptor(
                        doc.getObjectId("_id").toString(),
                        doc.getString("documento"),
                        doc.getString("nombre"),
                        doc.getString("direccion")
                );
                lista.add(s);
            }
        } finally {
            cursor.close();
        }
        return lista;
    }

    // Buscar por "documento" (tu código lo llama buscarPorId pasando el documento)
    public Suscriptor buscarPorId(String documento) {
        Document d = coleccion.find(Filters.eq("documento", documento)).first();

        if (d == null) return null;

        Suscriptor s = new Suscriptor();
        s.setId(d.getObjectId("_id").toHexString());
        s.setDocumento(d.getString("documento"));
        s.setNombre(d.getString("nombre"));
        s.setDireccion(d.getString("direccion"));

        return s;
    }

    // INSERTAR SUSCRIPTOR
    public void insertarSuscriptor(String documento, String nombre, String direccion) {
        Document suscriptor = new Document("documento", documento)
                .append("nombre", nombre)
                .append("direccion", direccion);

        coleccion.insertOne(suscriptor);
    }

    // BUSCAR POR DOCUMENTO -> devuelve Document crudo (si lo necesitas)
    public Document buscarPorDocumento(String documento) {
        Document filtro = new Document("documento", documento);
        return coleccion.find(filtro).first();
    }

    // LISTAR TODOS (Documentos)
    public List<Document> listar() {
        List<Document> lista = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                lista.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return lista;
    }

    // ACTUALIZAR SUSCRIPTOR
    public void actualizarSuscriptor(String documento, String nuevoNombre, String nuevaDireccion) {
        Document filtro = new Document("documento", documento);

        Document cambios = new Document("$set",
                new Document("nombre", nuevoNombre)
                        .append("direccion", nuevaDireccion)
        );

        coleccion.updateOne(filtro, cambios);
    }

    // ELIMINAR
    public void eliminarSuscriptor(String documento) {
        Document filtro = new Document("documento", documento);
        coleccion.deleteOne(filtro);
    }
}
