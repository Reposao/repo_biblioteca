package co.edu.uniquindio.poo;

import java.time.LocalDate;

public class Libro {

    private String codigo, isbn, autor, titulo, editorial;
    private int unidadesDisponibles;
    private LocalDate fechaPublicacion;
    private boolean estado;
    private double precio;  // Añadido el campo precio

    public Libro(String codigo, String isbn, String autor, String titulo, String editorial, int unidadesDisponibles, LocalDate fechaPublicacion, double precio) {
        this.codigo = codigo;
        this.isbn = isbn;
        this.autor = autor;
        this.editorial = editorial;
        this.titulo = titulo;
        this.unidadesDisponibles = unidadesDisponibles;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio; // Inicializar precio en el constructor
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getPrecio() { // Getter del precio
        return precio;
    }

    public void setPrecio(double precio) { // Setter del precio
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro [codigo=" + codigo + ", isbn=" + isbn + ", autor=" + autor + ", titulo=" + titulo + ", editorial="
                + editorial + ", unidadesDisponibles=" + unidadesDisponibles + ", fechaPublicacion=" + fechaPublicacion
                + ", estado=" + estado + ", precio=" + precio + "]"; // Añadir el precio al toString
    }

}
