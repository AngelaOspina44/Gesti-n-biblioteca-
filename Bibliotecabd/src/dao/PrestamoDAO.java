package dao;

import com.mongodb.client.FindIterable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modelo.Prestamo;

public class PrestamoDAO {

    private final MongoCollection<Document> coleccion;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PrestamoDAO(MongoDatabase database) {
        this.coleccion = database.getCollection("prestamos");
    }

    // ================================
    // REGISTRAR PRÉSTAMO
    // ================================
    public boolean registrarPrestamo(Prestamo p) {
        try {
            int codigoLibro = Integer.parseInt(p.getIdLibro());

            Date fechaP = p.getFechaPrestamo();
            if (fechaP == null) fechaP = new Date();

            Date fechaD = p.getFechaDevolucion(); // puede ser null

            Document d = new Document()
                .append("docsuscriptor", p.getIdSuscriptor())
                .append("codigolibro", codigoLibro)
                .append("fechaprestamo", fechaP)
                .append("fechadevolucion", fechaD)
                .append("devuelto", p.isDevuelto());

            coleccion.insertOne(d);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // ================================
    // OBTENER TODOS
    // ================================
    public List<Prestamo> obtenerTodos() {
        List<Prestamo> lista = new ArrayList<>();

        for (Document d : coleccion.find()) {
            String id = d.containsKey("_id") ? d.getObjectId("_id").toHexString() : null;

            String docsuscriptor = d.getString("docsuscriptor");
            Integer codigoLibro = d.getInteger("codigolibro");
            String idLibro = codigoLibro != null ? String.valueOf(codigoLibro) : "";

            Date fechaPrestamo = d.getDate("fechaprestamo");
            Date fechaDevolucion = d.getDate("fechadevolucion");
            boolean devuelto = d.getBoolean("devuelto", false);

            Prestamo p = new Prestamo(
                    id,
                    "admin",          // no manejas usuario aún
                    idLibro,
                    docsuscriptor,
                    fechaPrestamo,
                    fechaDevolucion,
                    devuelto
            );

            lista.add(p);
        }

        return lista;
    }

    // ================================
    // ¿LIBRO ESTÁ PRESTADO?
    // ================================
    public boolean libroEstaPrestado(String idLibro) {
        try {
            int codigo = Integer.parseInt(idLibro);

            Document d = coleccion.find(
                Filters.and(
                    Filters.eq("codigolibro", codigo),
                    Filters.eq("devuelto", false)
                )
            ).first();

            return d != null;
        } catch (Exception e) {
            return false;
        }
    }

    // ================================
    // MARCAR COMO DEVUELTO
    // ================================
    public boolean marcarComoDevuelto(String idPrestamo) {
        try {
            ObjectId oid = new ObjectId(idPrestamo);

            coleccion.updateOne(
                Filters.eq("_id", oid),
                new Document("$set",
                    new Document("devuelto", true)
                    .append("fechadevolucion", new Date())
                )
            );

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String> obtenerSuscriptoresConPrestamosVigentes() {
    List<String> lista = new ArrayList<>();

    // YA existe this.coleccion, úsala
    FindIterable<Document> documentos =
            coleccion.find(Filters.eq("devuelto", false));

    for (Document doc : documentos) {
        String idSuscriptor = doc.getString("docsuscriptor");
        lista.add(idSuscriptor);
    }

    return lista;
}

}
