package modelo;

import java.util.Date;

public class Prestamo {

    private String id;
    private String idUsuario;
    private String idLibro;      // lo tratamos como String en la UI (ej "1","2")
    private String idSuscriptor; // documento del suscriptor (ej "111")
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean devuelto;

    public Prestamo() {}

    public Prestamo(String id, String idUsuario, String idLibro, String idSuscriptor,
                    Date fechaPrestamo, Date fechaDevolucion, boolean devuelto) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.idSuscriptor = idSuscriptor;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getIdLibro() { return idLibro; }
    public void setIdLibro(String idLibro) { this.idLibro = idLibro; }

    public String getIdSuscriptor() { return idSuscriptor; }
    public void setIdSuscriptor(String idSuscriptor) { this.idSuscriptor = idSuscriptor; }

    public Date getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(Date fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public boolean isDevuelto() { return devuelto; }
    public void setDevuelto(boolean devuelto) { this.devuelto = devuelto; }
}
