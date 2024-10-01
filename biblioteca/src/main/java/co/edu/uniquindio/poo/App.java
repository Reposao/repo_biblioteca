package co.edu.uniquindio.poo;

import javax.swing.*;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", 0);

        // Crear algunos libros
        Libro libro1 = new Libro("L001", "978-3-16-148410-0", "Autor Uno", "cien años de soledad", "Editorial pachostunt", 5, LocalDate.of(2020, 1, 15), 10000);
        Libro libro2 = new Libro("L002", "978-3-16-148411-7", "Autor Dos", "cars", "Editorial alejostunt", 3, LocalDate.of(2021, 2, 20), 10000);

        // Agregar libros a la biblioteca
        biblioteca.getLibros().add(libro1);
        biblioteca.getLibros().add(libro2);

        // Crear un bibliotecario
        Bibliotecario bibliotecario = new Bibliotecario("Juan Pérez", "123456789", "juan.perez@ejemplo.com", 123456789, 1500.00, LocalDate.now());
        biblioteca.getBibliotecarios().add(bibliotecario);

        // Crear un estudiante
        Estudiante estudiante = new Estudiante("María Gómez", "987654321", "maria.gomez@ejemplo.com", 987654321, true);
        biblioteca.getEstudiantes().add(estudiante);

        // Crear un préstamo
        Prestamo prestamo = new Prestamo(libro1, "P001", LocalDate.now(), LocalDate.now().plusDays(7), bibliotecario, estudiante);
        biblioteca.getPrestamos().add(prestamo);

        // Crear un detalle de préstamo
        DetallePrestamo detalle = new DetallePrestamo(1, prestamo, libro1);
        JOptionPane.showMessageDialog(null, "Detalle del préstamo creado: " + detalle);

        // Consultar libro por código
        String codigoLibro = JOptionPane.showInputDialog("Ingrese el código del libro para consultar:");
        Libro libroConsultado = buscarLibroPorCodigo(biblioteca, codigoLibro);
        if (libroConsultado != null) {
            JOptionPane.showMessageDialog(null, "Libro encontrado: " + libroConsultado);
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
        }

        // Consultar cantidad de préstamos de un libro
        String nombreLibro = JOptionPane.showInputDialog("Ingrese el nombre del libro para consultar la cantidad de préstamos:");
        int cantidadPrestamos = contarPrestamosPorNombreLibro(biblioteca, nombreLibro);
        JOptionPane.showMessageDialog(null, "Cantidad de préstamos para el libro '" + nombreLibro + "': " + cantidadPrestamos);

        // Reemplazar un libro
        String codigoReemplazar = JOptionPane.showInputDialog("Ingrese el código del libro a reemplazar:");
        Libro nuevoLibro = new Libro("L003", "978-3-16-148412-4", "Autor Tres", "Título Tres", "Editorial Tres", 10, LocalDate.now(), 25000);
        reemplazarLibro(biblioteca, codigoReemplazar, nuevoLibro);

        // Adicionar libro al préstamo
        String codigoPrestamo = JOptionPane.showInputDialog("Ingrese el código del préstamo para adicionar libro:");
        String codigoLibroParaAdicionar = JOptionPane.showInputDialog("Ingrese el código del libro para adicionar al préstamo:");
        adicionarLibroAlPrestamo(biblioteca, codigoPrestamo, codigoLibroParaAdicionar);

        // Entregar préstamo
        String codigoPrestamoEntregar = JOptionPane.showInputDialog("Ingrese el código del préstamo para entregar:");
        entregarPrestamo(biblioteca, codigoPrestamoEntregar);

        // Consultar préstamo
        String codigoPrestamoConsultar = JOptionPane.showInputDialog("Ingrese el código del préstamo para consultar:");
        consultarPrestamo(biblioteca, codigoPrestamoConsultar);

        // Mostrar cantidad de préstamos realizados por cada empleado
        mostrarPrestamosPorEmpleado(biblioteca);

        // Reportes
        estudianteConMasPrestamos(biblioteca);
        totalDineroRecaudado(biblioteca);
        totalDineroAPagarBibliotecarios(biblioteca);

        JOptionPane.showMessageDialog(null, "Operaciones completadas. Estado actual de la biblioteca:\n" + biblioteca);
    }

    // Métodos auxiliares siguen igual...
    private static Libro buscarLibroPorCodigo(Biblioteca biblioteca, String codigo) {
        for (Libro libro : biblioteca.getLibros()) {
            if (libro.getCodigo().equals(codigo)) {
                return libro;
            }
        }
        return null; // No encontrado
    }

    private static int contarPrestamosPorNombreLibro(Biblioteca biblioteca, String nombre) {
        int contador = 0;
        for (Prestamo prestamo : biblioteca.getPrestamos()) {
            if (prestamo.getLibro().getTitulo().equalsIgnoreCase(nombre)) {
                contador++;
            }
        }
        return contador;
    }

    private static void reemplazarLibro(Biblioteca biblioteca, String codigo, Libro nuevoLibro) {
        for (Libro libro : biblioteca.getLibros()) {
            if (libro.getCodigo().equals(codigo)) {
                biblioteca.getLibros().remove(libro);
                biblioteca.getLibros().add(nuevoLibro);
                JOptionPane.showMessageDialog(null, "Libro reemplazado con éxito.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Código de libro no encontrado. No se pudo reemplazar.");
    }

    public static void adicionarLibroAlPrestamo(Biblioteca biblioteca, String codigoPrestamo, String codigoLibro) {
        Prestamo prestamo = null;
        for (Prestamo p : biblioteca.getPrestamos()) {
            if (p.getCodigo().equals(codigoPrestamo)) {
                prestamo = p;
                break;
            }
        }

        if (prestamo != null) {
            Libro libro = null;
            for (Libro l : biblioteca.getLibros()) {
                if (l.getCodigo().equals(codigoLibro)) {
                    libro = l;
                    break;
                }
            }

            if (libro != null && libro.getUnidadesDisponibles() > 0) {
                prestamo.setLibro(libro); // Asegúrate de que el método setLibro esté en tu clase Prestamo
                libro.setUnidadesDisponibles(libro.getUnidadesDisponibles() - 1);
                JOptionPane.showMessageDialog(null, "Libro añadido al préstamo con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Libro no disponible.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Préstamo no encontrado.");
        }
    }

    public static void entregarPrestamo(Biblioteca biblioteca, String codigoPrestamo) {
        Prestamo prestamo = null;
        for (Prestamo p : biblioteca.getPrestamos()) {
            if (p.getCodigo().equals(codigoPrestamo)) {
                prestamo = p;
                break;
            }
        }

        if (prestamo != null) {
            // Calcular costo del préstamo
            int diasPrestamo = calcularDias(prestamo.getFechaPrestamo(), prestamo.getFechaEntrega());
            double costo = diasPrestamo * 1.5; // Suponiendo un costo de 1.5 por día

            JOptionPane.showMessageDialog(null, "Costo del préstamo: " + costo);

            // Actualizar unidades disponibles del libro
            Libro libro = prestamo.getLibro();
            if (libro != null) {
                libro.setUnidadesDisponibles(libro.getUnidadesDisponibles() + 1);
            }

            // Aquí puedes eliminar el préstamo si es necesario
            biblioteca.getPrestamos().remove(prestamo);
            JOptionPane.showMessageDialog(null, "Préstamo entregado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Préstamo no encontrado.");
        }
    }

    // Método auxiliar para calcular la diferencia de días
    private static int calcularDias(LocalDate fechaInicio, LocalDate fechaFin) {
        return (int) (fechaFin.toEpochDay() - fechaInicio.toEpochDay());
    }

    public static void consultarPrestamo(Biblioteca biblioteca, String codigoPrestamo) {
        Prestamo prestamo = null;
        for (Prestamo p : biblioteca.getPrestamos()) {
            if (p.getCodigo().equals(codigoPrestamo)) {
                prestamo = p;
                break;
            }
        }

        if (prestamo != null) {
            JOptionPane.showMessageDialog(null, prestamo);
        } else {
            JOptionPane.showMessageDialog(null, "Préstamo no encontrado.");
        }
    }

    public static void mostrarPrestamosPorEmpleado(Biblioteca biblioteca) {
        for (Bibliotecario bibliotecario : biblioteca.getBibliotecarios()) {
            int contador = 0;
            for (Prestamo prestamo : biblioteca.getPrestamos()) {
                if (prestamo.getBibliotecario().equals(bibliotecario)) {
                    contador++;
                }
            }
            JOptionPane.showMessageDialog(null, bibliotecario.getNombre() + " ha realizado " + contador + " préstamos.");
        }
    }

    public static void estudianteConMasPrestamos(Biblioteca biblioteca) {
        Estudiante estudianteMasPrestamos = null;
        int maxPrestamos = 0;

        for (Estudiante estudiante : biblioteca.getEstudiantes()) {
            int contador = 0;
            for (Prestamo prestamo : biblioteca.getPrestamos()) {
                if (prestamo.getEstudiante().equals(estudiante)) {
                    contador++;
                }
            }

            if (contador > maxPrestamos) {
                maxPrestamos = contador;
                estudianteMasPrestamos = estudiante;
            }
        }

        if (estudianteMasPrestamos != null) {
            JOptionPane.showMessageDialog(null, "El estudiante con más préstamos es: " + estudianteMasPrestamos.getNombre() + " con " + maxPrestamos + " préstamos.");
        }
    }

    public static void totalDineroRecaudado(Biblioteca biblioteca) {
        double total = 0;
        for (Prestamo prestamo : biblioteca.getPrestamos()) {
            int diasPrestamo = calcularDias(prestamo.getFechaPrestamo(), prestamo.getFechaEntrega());
            total += diasPrestamo * 1.5; // Suponiendo un costo de 1.5 por día
        }
        JOptionPane.showMessageDialog(null, "Total de dinero recaudado por préstamos: " + total);
    }

    public static void totalDineroAPagarBibliotecarios(Biblioteca biblioteca) {
        double totalAPagar = 0;
        for (Bibliotecario bibliotecario : biblioteca.getBibliotecarios()) {
            totalAPagar += bibliotecario.getSalario(); // Suponiendo que el salario es lo que se debe pagar
        }
        JOptionPane.showMessageDialog(null, "Total de dinero a pagar a bibliotecarios: " + totalAPagar);
    }
}
