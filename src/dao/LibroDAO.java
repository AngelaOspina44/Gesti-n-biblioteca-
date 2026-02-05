package dao;

import bibliotecabd.ConexionMongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import model.Libro;       // 
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

    // ========== 2) BUSCAR POR CÓDIGO Y DEVOLVER LIBRO (objeto, no Document!) ==========
    public Libro buscarPorCodigo(int codigo) {
        Document d = coleccion.find(new Document("codigo", codigo)).first();

        if (d == null) return null;

        return new Libro(
                String.valueOf(d.getInteger("codigo")),  // ID del objeto
                d.getString("titulo"),
                d.getString("autor")
        );
    }

    // ========== 3) LISTAR TODOS LOS LIBROS COMO OBJETOS ==========
    public List<Libro> listarLibros() {
        List<Libro> lista = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                lista.add(
                        new Libro(
                                String.valueOf(d.getInteger("codigo")),
                                d.getString("titulo"),
                                d.getString("autor")
                        )
                );
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

    public List<Libro> obtenerTodos() {
    List<Libro> lista = new ArrayList<>();

    try {
        FindIterable<Document> documentos = coleccion.find();

        for (Document doc : documentos) {
            Libro libro = new Libro();

            libro.setId(String.valueOf(doc.getInteger("codigo"))); // ID tipo String
            libro.setTitulo(doc.getString("titulo"));
            libro.setAutor(doc.getString("autor"));

            lista.add(libro);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}



    public Libro buscarPorId(String idLibro) {
    try {
        int codigo = Integer.parseInt(idLibro); // Convertimos el ID tipo String a int
        return buscarPorCodigo(codigo); // Reusamos buscarPorCodigo
    } catch (NumberFormatException e) {
        System.out.println("Error: código de libro inválido → " + idLibro);
        return null;
        }
    }

}
