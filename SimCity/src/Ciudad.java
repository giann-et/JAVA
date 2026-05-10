import java.util.ArrayList;

public class Ciudad {
    private String nombre;
    private Alcalde alcalde;
    private ArrayList<Edificio> edificios;

    public Ciudad(String nombre, Alcalde alcalde) {
        this.nombre = nombre;
        this.alcalde = alcalde;
        this.edificios = new ArrayList<>();
    }

    public void agregarEdificio(Edificio e) { edificios.add(e); }
    public ArrayList<Edificio> getEdificios() { return edificios; }
    public Alcalde getAlcalde() { return alcalde; }
    public String getNombre() { return nombre; }

    public double getPromedioSeguridad() {
        if (edificios.isEmpty()) return 0;
        double suma = 0;
        for (Edificio e : edificios) suma += e.getSeguridad();
        return suma / edificios.size();
    }

    public double getPromedioIncendios() {
        if (edificios.isEmpty()) return 0;
        double suma = 0;
        for (Edificio e : edificios) suma += e.getIncendios();
        return suma / edificios.size();
    }

    public double getPromedioFelicidad() {
        if (edificios.isEmpty()) return 0;
        double suma = 0;
        for (Edificio e : edificios) suma += e.getFelicidad();
        return suma / edificios.size();
    }

    public double getTotalInvertido() {
        double total = 0;
        for (Edificio e : edificios) total += e.getPrecio();
        return total;
    }
}