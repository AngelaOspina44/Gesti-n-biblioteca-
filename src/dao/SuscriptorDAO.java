package dao;

import bibliotecabd.ConexionMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class SuscriptorDAO {

    private final MongoCollection<Document> coleccion;

    public SuscriptorDAO() {
        MongoDatabase db = ConexionMongo.getDatabase();
        this.coleccion = db.getCollection("suscriptores");
    }

    // INSERTAR SUSCRIPTOR
    public void insertarSuscriptor(String documento, String nombre, String direccion) {
        Document suscriptor = new Document("documento", documento)
                .append("nombre", nombre)
                .append("direccion", direccion);

        coleccion.insertOne(suscriptor);
    }

    // BUSCAR POR DOCUMENTO
    public Document buscarPorDocumento(String documento) {
        Document filtro = new Document("documento", documento);
        return coleccion.find(filtro).first();
    }

    // LISTAR TODOS
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
