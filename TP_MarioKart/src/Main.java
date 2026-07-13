import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Componentes disponibles
        Componente[] ruedas = {
                new Componente("Rueda Pequena", -1, -2, 2),
                new Componente("Rueda Mediana", 0, 1, 1),
                new Componente("Rueda Monstruo", 2, 2, 0)
        };

        Componente[] chasis = {
                new Componente("Chasis Liviano", -2, -2, 2),
                new Componente("Chasis Medio", 1, 0, 1),
                new Componente("Chasis Pesado", 2, 2, 0)
        };

        // Circuitos con vueltas configuradas
        Pista[] pistas = {
                new Pista("Circuito Mario", 100, 2),
                new Pista("Senda Arcoiris", 150, 3),
                new Pista("Castillo de Bowser", 200, 3)
        };

        System.out.println("=== BIENVENIDO A MARIO KART ===");
        System.out.print("Introduce tu nombre de piloto: ");
        String nombreUsuario = scanner.nextLine();

        System.out.println("\nSelecciona tu vehiculo:\n1. Auto\n2. Moto");
        int opVehiculo = scanner.nextInt();
        Vehiculo vehiculoUsuario = (opVehiculo == 2) ? new Moto() : new Auto();

        System.out.println("\nSelecciona tus ruedas:");
        for (int i = 0; i < ruedas.length; i++) {
            System.out.println((i+1) + ". " + ruedas[i].nombre);
        }
        int opRuedas = scanner.nextInt() - 1;

        System.out.println("\nSelecciona tu chasis:");
        for (int i = 0; i < chasis.length; i++) {
            System.out.println((i+1) + ". " + chasis[i].nombre);
        }
        int opChasis = scanner.nextInt() - 1;

        System.out.println("\nSelecciona la pista:");
        for (int i = 0; i < pistas.length; i++) {
            System.out.println((i+1) + ". " + pistas[i].nombre + " (" + pistas[i].distanciaPorVuelta + "m por vuelta - " + pistas[i].totalVueltas + " vueltas)");
        }
        int opPista = scanner.nextInt() - 1;
        Pista pistaSeleccionada = pistas[opPista];

        System.out.println("\nSelecciona tipo de carrera:\n1. Aleatorio\n2. Clasico (Vs. Luigi - Peach - Browser)");
        int modoRival = scanner.nextInt();

        ArrayList<Corredor> competidores = new ArrayList<>();
        Corredor.reiniciarPuestos();

        // Mario stats base ficticio para el jugador
        Corredor jugador = new Corredor(nombreUsuario, 40, 3, 1, vehiculoUsuario, ruedas[opRuedas], chasis[opChasis], pistaSeleccionada);
        competidores.add(jugador);

        if (modoRival == 2) {
            competidores.add(new Corredor("Luigi", 39, 3, 2, new Auto(), ruedas[1], chasis[1], pistaSeleccionada));
            competidores.add(new Corredor("Peach", 42, 2, 3, new Moto(), ruedas[0], chasis[0], pistaSeleccionada));
            competidores.add(new Corredor("Bowser", 45, 5, 0, new Auto(), ruedas[2], chasis[2], pistaSeleccionada));
        } else {
            String[] nombresRivales = {"Yoshi", "Toad", "Donkey Kong"};
            for (String nombreR : nombresRivales) {
                Vehiculo vRival = random.nextBoolean() ? new Auto() : new Moto();
                Componente rRival = ruedas[random.nextInt(ruedas.length)];
                Componente cRival = chasis[random.nextInt(chasis.length)];
                competidores.add(new Corredor(nombreR, 38 + random.nextInt(5), 2 + random.nextInt(3), random.nextInt(4), vRival, rRival, cRival, pistaSeleccionada));
            }
        }

        System.out.println("\n=== ¡¡SE LARGA LA CARRERA EN " + pistaSeleccionada.nombre.toUpperCase() + "!! ===");

        for (Corredor c : competidores) {
            c.start();
        }

        for (Corredor c : competidores) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n=========================================");
        System.out.println("         RESULTADOS DE LA CARRERA        ");
        System.out.println("=========================================");

        competidores.sort((c1, c2) -> Integer.compare(c1.getPuestoLlegada(), c2.getPuestoLlegada()));

        System.out.println("¡GANADOR: " + competidores.get(0).getNombreCorredor() + "!\n");

        System.out.println("--- ORDEN DE LLEGADA ---");
        for (Corredor c : competidores) {
            System.out.println(c.getPuestoLlegada() + "° Puesto: " + c.getNombreCorredor() + " - Tiempo: " + c.getTiempoTranscurrido() + " segundos.");
        }

        System.out.println("\n--- CONFIGURACION DE CADA CORREDOR ---");
        for (Corredor c : competidores) {
            c.mostrarConfiguracion();
        }
    }
}