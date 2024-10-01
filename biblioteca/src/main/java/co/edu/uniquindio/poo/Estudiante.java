package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.Biblioteca;

import java.util.Collection;
import java.util.LinkedList;

public class Estudiante extends Persona {

    private boolean estado;
    private Collection<Prestamo> prestamos;

    public Estudiante(String nombre, String cedula, String correo, int telefono, boolean estado) {
        super(nombre, cedula, correo, telefono);
        this.estado = estado;
        prestamos = new LinkedList<>();
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Collection<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Collection<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamos.contains(prestamo)) {
            throw new IllegalArgumentException("El préstamo ya está registrado.");
        }
        prestamos.add(prestamo);
    }

    public int getNumeroPrestamos() {
        return prestamos.size();
    }

    public double getTotalRecaudado() {
        double total = 0.0;
        for (Prestamo prestamo : prestamos) {
            total += prestamo.getTotal(); // Asume que `getTotal()` está implementado en Prestamo
        }
        return total;
    }

    @Override
    public String toString() {
        return "Estudiante [nombre=" + getNombre() + ", cedula=" + getCedula() + ", estado=" + estado +
                ", numeroPrestamos=" + getNumeroPrestamos() + "]";
    }

}
