package dao;

import bibliotecabd.ConexionMongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import model.Libro;
import org.bson.Document;

public class LibroDAO {

    private final MongoCollection<Document> coleccion;

    public LibroDAO() {
        MongoDatabase db = ConexionMongo.getDatabase();
        this.coleccion = db.getCollection("libros");
    }

    // ========== 1) INSERTAR LIBRO ==========
    public void insertarLibro(int codigo, String titulo, String autor) {
        Document libro = new Document("codigo", codigo)
                .append("titulo", titulo)
                .append("autor", autor);

        coleccion.insertOne(libro);
    }

    // ========== 2) BUSCAR POR CÓDIGO (INT) ==========
    public Libro buscarPorCodigo(int codigo) {
        Document d = coleccion.find(Filters.eq("codigo", codigo)).first();
        if (d == null) return null;

        Libro l = new Libro();
        // guardamos el código como id (STRING) para compatibilidad con la GUI
        l.setId(String.valueOf(codigo));
        l.setCodigo(codigo); // si tu modelo tiene campo 'codigo' numérico
        l.setTitulo(d.getString("titulo"));
        l.setAutor(d.getString("autor"));

        return l;
    }

    // ========== 3) LISTAR TODOS LOS LIBROS COMO OBJETOS ==========
    public List<Libro> listarLibros() {
        List<Libro> lista = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Integer codigo = d.getInteger("codigo");
                Libro l = new Libro();
                l.setId(codigo != null ? String.valueOf(codigo) : "");
                l.setCodigo(codigo != null ? codigo : 0);
                l.setTitulo(d.getString("titulo"));
                l.setAutor(d.getString("autor"));
                lista.add(l);
            }
        } finally {
            cursor.close();
        }
        return lista;
    }

    // ========== 4) ACTUALIZAR ==========
    public void actualizarLibro(int codigo, String nuevoTitulo, String nuevoAutor) {
        Document filtro = new Document("codigo", codigo);

        Document cambios = new Document("$set",
                new Document("titulo", nuevoTitulo)
                        .append("autor", nuevoAutor)
        );

        coleccion.updateOne(filtro, cambios);
    }

    // ========== 5) ELIMINAR ==========
    public void eliminarLibro(int codigo) {
        coleccion.deleteOne(new Document("codigo", codigo));
    }

    // ========== 6) obtenerTodos() (compatibilidad con GUI) ==========
    // devuelve todos los libros como List<Libro>, id será el codigo en String
    public List<Libro> obtenerTodos() {
        List<Libro> lista = new ArrayList<>();

        try {
            FindIterable<Document> documentos = coleccion.find();
            for (Document doc : documentos) {
                Integer codigo = doc.getInteger("codigo");
                Libro libro = new Libro();
                libro.setId(codigo != null ? String.valueOf(codigo) : "");
                libro.setCodigo(codigo != null ? codigo : 0);
                libro.setTitulo(doc.getString("titulo"));
                libro.setAutor(doc.getString("autor"));
                lista.add(libro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ========== 7) buscarPorId(String idLibro) ==========
    // tu GUI llama buscarPorId(p.getIdLibro()), que pasa un String (codigo).
    public Libro buscarPorId(String idLibro) {
        try {
            int codigo = Integer.parseInt(idLibro);
            return buscarPorCodigo(codigo);
        } catch (NumberFormatException e) {
            // idLibro no es numérico -> no existe
            return null;
        }
    }
}
