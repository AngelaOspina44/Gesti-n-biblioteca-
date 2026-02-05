package bibliotecabd;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {

    private static final String URI = "mongodb://localhost:27017";
    private static final String BD = "Biblioteca";

    private static MongoClient mongoClient = null;

    // Conectar al servidor Mongo
    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient;
    }

    // Obtener la base de datos
    public static MongoDatabase getDatabase() {
        return getMongoClient().getDatabase(BD);
    }
}
