package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Libro> libros;
    private List<Bibliotecario> bibliotecarios;
    private List<Estudiante> estudiantes;
    private List<Prestamo> prestamos;

    public Biblioteca(String nombre, int capacidad) {
        this.nombre = nombre;
        this.libros = new ArrayList<>(capacidad);
        this.bibliotecarios = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    @Override
    public String toString() {
        return "Biblioteca: " + nombre + "\n" +
                "Libros: " + libros.size() + "\n" +
                "Bibliotecarios: " + bibliotecarios.size() + "\n" +
                "Estudiantes: " + estudiantes.size() + "\n" +
                "Préstamos: " + prestamos.size();
    }
}
