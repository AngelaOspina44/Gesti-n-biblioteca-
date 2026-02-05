package modelo;

public class Prestamo {

    private String id;
    private String idUsuario;
    private String idLibro;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private boolean devuelto;

    public Prestamo(String id, String idUsuario, String idLibro, 
            String fechaPrestamo, String fechaDevolucion, boolean devuelto) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public Prestamo() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getIdLibro() { return idLibro; }
    public void setIdLibro(String idLibro) { this.idLibro = idLibro; }

    public String getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(String fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public String getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(String fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public boolean isDevuelto() { return devuelto; }
    public void setDevuelto(boolean devuelto) { this.devuelto = devuelto; }
}
