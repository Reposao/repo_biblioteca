package co.edu.uniquindio.poo;

public class DetallePrestamo {

    private int cantidad;
    private Prestamo prestamo;
    private Libro libro;
    private double subTotal;

    public DetallePrestamo(int cantidad, Prestamo prestamo, Libro libro) {
        this.cantidad = cantidad;
        this.prestamo = prestamo;
        this.libro = libro;
        this.subTotal = calcularSubtotal(); // Asignación de subtotal
    }

    private double calcularSubtotal() {
        return cantidad * libro.getPrecio(); // Asume que libro tiene un método getPrecio()

    }



    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subTotal = calcularSubtotal(); // Actualiza el subtotal al cambiar la cantidad
    }
    public void actualizarSubtotal() {
        this.subTotal = calcularSubtotal();
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "DetallePrestamo [cantidad=" + cantidad + ", prestamo=" + prestamo + ", libro=" + libro + ", subTotal="
                + subTotal + "]";
    }



}