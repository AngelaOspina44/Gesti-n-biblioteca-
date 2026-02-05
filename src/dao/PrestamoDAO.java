package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import modelo.Prestamo;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PrestamoDAO {

    private MongoCollection<Document> coleccion;

    public PrestamoDAO(MongoDatabase database) {
        this.coleccion = database.getCollection("prestamos");
    }

    // ==================== REGISTRAR =====================
    public boolean registrarPrestamo(Prestamo p) {
        try {
            // Guardamos idLibro como NUMBER para que coincida con la colección libros
            int codigoLibro = Integer.parseInt(p.getIdLibro());

            Document d = new Document()
                .append("idUsuario", p.getIdUsuario())
                .append("idLibro", codigoLibro)
                .append("fechaPrestamo", p.getFechaPrestamo())
                .append("fechaDevolucion", p.getFechaDevolucion())
                .append("devuelto", p.isDevuelto());

            coleccion.insertOne(d);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==================== OBTENER TODOS =====================
    public List<Prestamo> obtenerTodos() {
        List<Prestamo> lista = new ArrayList<>();

        for (Document d : coleccion.find()) {

            // Si no existe "devuelto", por defecto es false
            Boolean devuelto = d.getBoolean("devuelto");
            if (devuelto == null) {
                devuelto = false;
            }

            // idLibro viene como entero → convertirlo a String
            Integer codigoLibro = d.getInteger("idLibro");

            Prestamo p = new Prestamo(
                d.getObjectId("_id").toString(),
                d.getString("idUsuario"),
                codigoLibro != null ? String.valueOf(codigoLibro) : null,
                d.getString("fechaPrestamo"),
                d.getString("fechaDevolucion"),
                devuelto
            );

            lista.add(p);
        }

        return lista;
    }

    // ==================== LIBRO PRESTADO? =====================
    public boolean libroEstaPrestado(String idLibro) {
        try {
            int codigoLibro = Integer.parseInt(idLibro);

            Document d = coleccion.find(
                Filters.and(
                    Filters.eq("idLibro", codigoLibro),
                    Filters.eq("devuelto", false)
                )
            ).first();

            return d != null;

        } catch (Exception e) {
            return false;
        }
    }

    // ==================== MARCAR DEVUELTO =====================
    public boolean marcarComoDevuelto(String idPrestamo) {
        try {
            ObjectId oid = new ObjectId(idPrestamo);

            coleccion.updateOne(
                Filters.eq("_id", oid),
                new Document("$set", new Document("devuelto", true))
            );
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
