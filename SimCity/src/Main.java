import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[][] datos = {
                {"Rascascielos","Maravilla","0","0","10","900000"},{"Torre Eiffel","Maravilla","0","0","10","850000"},{"Arco del triunfo","Maravilla","0","0","9","700000"},{"Pirámide","Maravilla","0","0","9","600000"},{"Obelisco","Maravilla","0","0","8","450000"},{"Planta de energía eólica","Planta energética","0","0","10","500000"},{"Planta de energía solar","Planta energética","0","0","9","400000"},{"Planta de energía hidroeléctrica","Planta energética","0","0","8","300000"},{"Planta de energía de carbón","Planta energética","0","0","4","150000"},{"Empresa de agua corriente","Planta de agua","0","0","10","750000"},{"Obras de cañerias para agua potable","Planta de agua","0","0","9","500000"},{"Tanques de agua por manzana","Planta de agua","0","10","9","350000"},{"Pozos de agua","Planta de agua","0","0","7","200000"},{"Mega estación de policía","Seguridad","10","0","10","400000"},{"Estación de polocía grande","Seguridad","9","0","9","300000"},{"Estación de policía mediana","Seguridad","7","0","5","200000"},{"Estación de polocía chica","Seguridad","5","0","2","100000"},{"Mega estación de bomberos","Incendios","0","10","10","450000"},{"Estación de bomberos grande","Incendios","0","8","8","350000"},{"Estación de bomberos mediana","Incendios","0","7","6","250000"},{"Estación de bomberos chica","Incendios","0","6","5","150000"},{"Rutas","Caminos","0","0","10","400000"},{"Autopistas","Caminos","0","0","9","300000"},{"Avenidas","Caminos","0","0","8","200000"},{"Calles de asfalto","Caminos","0","0","6","150000"},{"Calles de tierra","Caminos","0","0","1","50000"},{"Reserva natural","Ecología","0","0","10","500000"},{"Parque polideportivo","Ecología","0","0","9","300000"},{"Parque grande","Ecología","0","0","8","200000"},{"Plaza","Ecología","0","0","7","100000"}
        };

        Alcalde alcalde = new Alcalde(Utiles.leerString("Nombre Alcalde: "), Utiles.leerString("Apellido: "));
        Ciudad miCiudad = new Ciudad(Utiles.leerString("Nombre Ciudad: "), alcalde);
        ArrayList<String> tiposComprados = new ArrayList<>();

        while (true) {
            System.out.println("\nPresupuesto: $" + miCiudad.getAlcalde().getDinero());
            for (int i = 0; i < datos.length; i++) {
                System.out.printf("%d) %-35s | %-18s | $%s\n", i, datos[i][0], datos[i][1], datos[i][5]);
            }
            String entrada = Utiles.leerString("Selección (ID, TORG o FIN): ");

            if (entrada.equalsIgnoreCase("TORG")) {
                miCiudad.getAlcalde().setDinero(miCiudad.getAlcalde().getDinero() + 1000000);
                continue;
            }
            if (entrada.equalsIgnoreCase("FIN")) {
                String[] oblig = {"Planta energética", "Planta de agua", "Seguridad", "Incendios", "Caminos", "Ecología"};
                boolean listo = true;
                for (String t : oblig) if (!tiposComprados.contains(t)) { System.out.println("Falta: " + t); listo = false; }
                if (listo) break; else continue;
            }

            try {
                int id = Integer.parseInt(entrada);
                String tipo = datos[id][1];
                double precio = Double.parseDouble(datos[id][5]);
                if (tiposComprados.contains(tipo) && !tipo.equals("Maravilla")) {
                    System.out.println("Ya tienes este tipo.");
                } else if (miCiudad.getAlcalde().getDinero() < precio) {
                    System.out.println("No alcanza.");
                } else {
                    miCiudad.getAlcalde().setDinero(miCiudad.getAlcalde().getDinero() - precio);
                    tiposComprados.add(tipo);
                    miCiudad.agregarEdificio(new Edificio(datos[id][0], tipo, Integer.parseInt(datos[id][2]), Integer.parseInt(datos[id][3]), Integer.parseInt(datos[id][4]), precio));
                }
            } catch (Exception e) { System.out.println("Inválido."); }
        }

        int op = 0;
        while (op != 8) {
            System.out.println("\n--- MENÚ " + miCiudad.getNombre().toUpperCase() + " ---");
            System.out.println("1. Datos generales\n2. Seguridad\n3. Incendios\n4. Felicidad\n5. Mayor/Menor valor\n6. Total invertido\n7. Mayor/Menor aporte\n8. Terminar");
            op = Utiles.verificarEntero("Opción: ");

            switch (op) {
                case 1:
                    System.out.println("Alcalde: " + miCiudad.getAlcalde().getNombreCompleto());
                    System.out.println("Edificios comprados:");
                    for (Edificio e : miCiudad.getEdificios()) System.out.println("- " + e.getNombre());
                    break;
                case 2: System.out.println("Promedio Seguridad: " + miCiudad.getPromedioSeguridad()); break;
                case 3: System.out.println("Promedio Incendios: " + miCiudad.getPromedioIncendios()); break;
                case 4: System.out.println("Promedio Felicidad: " + miCiudad.getPromedioFelicidad()); break;
                case 5:
                    Edificio maV = miCiudad.getEdificios().get(0), miV = miCiudad.getEdificios().get(0);
                    for (Edificio e : miCiudad.getEdificios()) {
                        if (e.getPrecio() > maV.getPrecio()) maV = e;
                        if (e.getPrecio() < miV.getPrecio()) miV = e;
                    }
                    System.out.println("Mayor valor: " + maV.getNombre() + " | Menor: " + miV.getNombre());
                    break;
                case 6: System.out.println("Inversión total: $" + miCiudad.getTotalInvertido()); break;
                case 7:
                    Edificio maA = miCiudad.getEdificios().get(0), miA = miCiudad.getEdificios().get(0);
                    for (Edificio e : miCiudad.getEdificios()) {
                        int sumaE = e.getSeguridad() + e.getIncendios() + e.getFelicidad();
                        int sumaMa = maA.getSeguridad() + maA.getIncendios() + maA.getFelicidad();
                        int sumaMi = miA.getSeguridad() + miA.getIncendios() + miA.getFelicidad();
                        if (sumaE > sumaMa) maA = e;
                        if (sumaE < sumaMi) miA = e;
                    }
                    System.out.println("Edificio que más aporta: " + maA.getNombre() + " | Menos: " + miA.getNombre());
                    break;
            }
        }
    }
}