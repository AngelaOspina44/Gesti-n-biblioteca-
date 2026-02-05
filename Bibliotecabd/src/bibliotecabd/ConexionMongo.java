package bibliotecabd;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {    
    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "biblioteca";
    private static MongoClient mongoClient;

    static {
        try {
            System.out.println("=== CONECTANDO A MONGODB ===");
            System.out.println("URI: " + URI);
            System.out.println("Base de datos: " + DB_NAME);
            
            mongoClient = MongoClients.create(URI);
            
            // Verificar conexión
            System.out.println("Conexión exitosa a MongoDB");
            
        } catch (Exception e) {
            System.err.println("ERROR en conexión MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static MongoDatabase getDatabase() {
        System.out.println("Obteniendo base de datos: " + DB_NAME);
        MongoDatabase db = mongoClient.getDatabase(DB_NAME);
        System.out.println("Base de datos obtenida: " + db.getName());
        return db;
    }

    // opcional: cerrar conexión
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
