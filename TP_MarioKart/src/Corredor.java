import java.util.Random;

public class Corredor extends Thread {
    private String nombre;
    private double velocidadBase;
    private int pesoBase;
    private int driftBase;

    private double velocidadFinal;
    private int estabilidadFinal;
    private int driftFinal;

    private double distanciaRecorrida = 0;
    private int tiempoTranscurrido = 0;
    private Pista pista;
    private Random random = new Random();

    private static int puestoActual = 1;
    private int puestoLlegada;

    public Corredor(String nombre, double velocidadBase, int pesoBase, int driftBase,
                    Vehiculo vehiculo, Componente rueda, Componente chasis, Pista pista) {
        this.nombre = nombre;
        this.velocidadBase = velocidadBase;
        this.pesoBase = pesoBase;
        this.driftBase = driftBase;
        this.pista = pista;

        // Cálculo de estadísticas combinando Personaje + Vehículo + Componentes
        int pesoTotal = this.pesoBase + vehiculo.getPeso() + rueda.pesoMod + chasis.pesoMod;
        this.estabilidadFinal = vehiculo.getEstabilidad() + rueda.estabilidadMod + chasis.estabilidadMod;
        this.driftFinal = this.driftBase + vehiculo.getDrift() + rueda.driftMod + chasis.driftMod;

        // Resta 3m/s por cada punto de peso
        this.velocidadFinal = this.velocidadBase - (pesoTotal * 3);
        if (this.velocidadFinal < 5) this.velocidadFinal = 5;
    }

    @Override
    public void run() {
        int vueltaActual = 1;

        while (vueltaActual <= pista.totalVueltas) {
            try {
                Thread.sleep(1000);
                tiempoTranscurrido++;

                double avanceEsteSegundo = velocidadFinal + (random.nextInt(5) - 2);

                // Penalización por Estabilidad (Caída)
                if (estabilidadFinal < 10) {
                    int probabilidadCaida = (10 - estabilidadFinal) * 5;
                    if (random.nextInt(100) < probabilidadCaida) {
                        avanceEsteSegundo -= 5;
                        System.out.println("[" + nombre + "] ¡Se cayó y perdió 5 metros!");
                    }
                }

                // Boost por Drift
                if (driftFinal > 10) {
                    int probabilidadBoost = (driftFinal - 10) * 5;
                    if (random.nextInt(100) < probabilidadBoost) {
                        avanceEsteSegundo += 10;
                        System.out.println("[" + nombre + "] ¡Metió DRIFT! Boost de +10 metros.");
                    }
                }

                if (avanceEsteSegundo < 0) avanceEsteSegundo = 0;
                distanciaRecorrida += avanceEsteSegundo;

                // Controlar cambio de vueltas
                int vueltaCalculada = (int) (distanciaRecorrida / pista.distanciaPorVuelta) + 1;

                if (vueltaCalculada > vueltaActual && vueltaActual < pista.totalVueltas) {
                    System.out.println(">>> [" + nombre + "] ¡PASÓ A LA VUELTA " + vueltaCalculada + "! <<<");
                    vueltaActual = vueltaCalculada;
                } else if (distanciaRecorrida >= pista.getLongitudTotal()) {
                    break;
                }

                System.out.printf("%s [Vuelta %d] lleva %.1f metros totales...\n", nombre, vueltaActual, distanciaRecorrida);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Asignación de puestos de forma sincronizada
        synchronized (Corredor.class) {
            this.puestoLlegada = puestoActual;
            puestoActual++;
        }
        System.out.println(">>> ¡" + nombre + " HA CRUZADO LA META! <<<");
    }

    public String getNombreCorredor() { return nombre; }
    public int getTiempoTranscurrido() { return tiempoTranscurrido; }
    public int getPuestoLlegada() { return puestoLlegada; }
    public void mostrarConfiguracion() {
        System.out.println("- " + nombre + " | Vel. Final: " + velocidadFinal + "m/s | Estabilidad: " + estabilidadFinal + " | Drift: " + driftFinal);
    }

    public static void reiniciarPuestos() { puestoActual = 1; }
}