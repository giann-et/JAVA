public class Alcalde {
    private String nombre, apellido;
    private double dinero;

    public Alcalde(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dinero = 1000000;
    }

    public String getNombreCompleto() { return nombre + " " + apellido; }
    public double getDinero() { return dinero; }
    public void setDinero(double dinero) { this.dinero = dinero; }
}