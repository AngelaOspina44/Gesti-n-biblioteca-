package dao;

import bibliotecabd.ConexionMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import org.bson.Document;

public class UsuarioDAO {

    private final MongoCollection<Document> coleccion;

    public UsuarioDAO() {
        MongoDatabase db = ConexionMongo.getDatabase();
        this.coleccion = db.getCollection("usuarios");
    }

    // 🔹 Validar usuario para login
    public boolean validarLogin(String nombre, String password) {
        Document filtro = new Document("nombre", nombre)
                              .append("password", password);

        return coleccion.find(filtro).first() != null;
    }

    // 🔹 Listar todos los usuarios desde Mongo
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                lista.add(new Usuario(
                        doc.getString("id"),
                        doc.getString("nombre")
                ));
            }
        } finally {
            cursor.close();
        }
        return lista;
    }

    // 🔹 Buscar usuario por Id
    public Usuario buscarPorId(String idUsuario) {
        Document filtro = new Document("id", idUsuario);
        Document doc = coleccion.find(filtro).first();

        if(doc == null) return null; // No encontrado

        return new Usuario(
                doc.getString("id"),
                doc.getString("nombre")
        );
    }
}
