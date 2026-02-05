package model;

public class Suscriptor {

    private String id;        // Mongo ObjectId en String
    private String documento;
    private String nombre;
    private String direccion;

    // Constructor vacío (necesario para crear con new Suscriptor() y usar setters)
    public Suscriptor() {
    }

    // Constructor completo (tu constructor original)
    public Suscriptor(String id, String documento, String nombre, String direccion) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters
    public String getId() { return id; }
    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }

    // Setters (necesarios porque el DAO los usa)
    public void setId(String id) { this.id = id; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
