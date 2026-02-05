package model;

public class Libro {
    private String id;     // se usará para mostrar en combos, será = codigo en string
    private int codigo;    // código real del libro en Mongo
    private String titulo;
    private String autor;

    public Libro() {}

    public Libro(String id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Libro(int codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.id = String.valueOf(codigo);  // id = código convertido a String
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { 
        this.codigo = codigo; 
        this.id = String.valueOf(codigo); // mantener sincronizado
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    @Override
    public String toString() { 
        return id + " - " + titulo; 
    }
}
