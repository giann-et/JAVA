// Clase Padre
public class Vehiculo {
    protected String tipo;
    protected int estabilidad;
    protected int peso;
    protected int drift;

    public Vehiculo(String tipo, int estabilidad, int peso, int drift) {
        this.tipo = tipo;
        this.estabilidad = estabilidad;
        this.peso = peso;
        this.drift = drift;
    }

    public int getEstabilidad() { return estabilidad; }
    public int getPeso() { return peso; }
    public int getDrift() { return drift; }
    public String getTipo() { return tipo; }
}

// Hijos
class Auto extends Vehiculo {
    public Auto() { super("Auto", 10, 10, 5); }
}

class Moto extends Vehiculo {
    public Moto() { super("Moto", 5, 5, 8); }
}

// Estructuras de soporte requeridas por el enunciado
class Componente {
    String nombre;
    int estabilidadMod;
    int pesoMod;
    int driftMod;

    public Componente(String nombre, int estabilidadMod, int pesoMod, int driftMod) {
        this.nombre = nombre;
        this.pesoMod = pesoMod;
        this.driftMod = driftMod;
        this.estabilidadMod = estabilidadMod;
    }
}

class Pista {
    String nombre;
    double distanciaPorVuelta;
    int totalVueltas;

    public Pista(String nombre, double distanciaPorVuelta, int totalVueltas) {
        this.nombre = nombre;
        this.distanciaPorVuelta = distanciaPorVuelta;
        this.totalVueltas = totalVueltas;
    }

    public double getLongitudTotal() {
        return distanciaPorVuelta * totalVueltas;
    }
}