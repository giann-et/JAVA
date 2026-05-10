public class Edificio {
    private String nombre, tipo;
    private int seguridad, incendios, felicidad;
    private double precio;

    public Edificio(String nombre, String tipo, int seg, int inc, int fel, double precio) {
        this.nombre = nombre; this.tipo = tipo; this.seguridad = seg;
        this.incendios = inc; this.felicidad = fel; this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getSeguridad() { return seguridad; }
    public int getIncendios() { return incendios; }
    public int getFelicidad() { return felicidad; }
    public double getPrecio() { return precio; }
}