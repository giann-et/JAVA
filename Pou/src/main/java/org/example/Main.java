package org.example;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManejadorDB db = new ManejadorDB();
        Scanner teclado = new Scanner(System.in);
        Random azar = new Random();

        List<Alimento> alimentosDisponibles = db.listaComidas();
        if (alimentosDisponibles.isEmpty()) {
            db.guardarCambios(new Alimento("Hamburguesa", -30, 20, 15, 5));
            db.guardarCambios(new Alimento("Banana", -15, 10, 2, 8));
            db.guardarCambios(new Alimento("Caramelos", -5, 25, 8, -5));
            alimentosDisponibles = db.listaComidas();
        }

        Pou miPou = db.cargarPou(1L);
        if (miPou == null) {
            System.out.println("--- CREANDO NUEVO POU ---");
            System.out.print("¿Cómo se va a llamar tu amigo?: ");
            String nombrePou = teclado.nextLine();
            miPou = new Pou(nombrePou);
            db.guardarCambios(miPou);
        }

        boolean jugando = true;
        while (jugando) {
            System.out.println("\n=================================");
            System.out.println(" ESTADO DE: " + miPou.getNombre().toUpperCase());
            System.out.println("=================================");
            System.out.println("-> Hambre: " + miPou.getHambre() + "/100");
            System.out.println("-> Felicidad: " + miPou.getFelicidad() + "/100");
            System.out.println("-> Suciedad: " + miPou.getSuciedad() + "/100");
            System.out.println("-> Energía: " + miPou.getEnergia() + "/100");
            System.out.println("---------------------------------");
            System.out.println("Menú de opciones:");
            System.out.println("1) Dar de comer");
            System.out.println("2) Bañar");
            System.out.println("3) Jugar");
            System.out.println("4) Dormir");
            System.out.println("5) Acariciar");
            System.out.println("6) Guardar y Salir");
            System.out.print("Elegí qué hacer: ");

            int opcion = teclado.nextInt();

            if (opcion == 6) {
                jugando = false;
                System.out.println("¡Partida guardada! Nos vemos.");
                break;
            }

            if (opcion >= 1 && opcion <= 5 && azar.nextInt(100) < 20) {
                System.out.println("\n⚠️ [¡El Pou no te dio bola y se portó mal! Le llamaste la atención]");
                miPou.setFelicidad(miPou.getFelicidad() - 10);
                miPou.verificarLimites();
                db.guardarCambios(miPou);
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n¿Qué le vas a dar de comer a " + miPou.getNombre() + "?");
                    for (int i = 0; i < alimentosDisponibles.size(); i++) {
                        Alimento al = alimentosDisponibles.get(i);
                        System.out.println((i + 1) + ". " + al.getNombre());
                    }
                    int seleccion = teclado.nextInt();
                    if (seleccion > 0 && seleccion <= alimentosDisponibles.size()) {
                        Alimento elegido = alimentosDisponibles.get(seleccion - 1);
                        miPou.setHambre(miPou.getHambre() + elegido.getPuntosHambre());
                        miPou.setFelicidad(miPou.getFelicidad() + elegido.getPuntosFelicidad());
                        miPou.setSuciedad(miPou.getSuciedad() + elegido.getPuntosSuciedad());
                        miPou.setEnergia(miPou.getEnergia() + elegido.getPuntosEnergia());
                        System.out.println("Le diste de comer: " + elegido.getNombre());
                    } else {
                        System.out.println("Esa comida no existe.");
                    }
                    break;

                case 2:
                    System.out.println("\n🧼 Lo bañaste bien y quedó limpito.");
                    miPou.setSuciedad(0);
                    miPou.setFelicidad(miPou.getFelicidad() + 12);
                    break;

                case 3:
                    System.out.println("\n⚽ Jugaste un buen rato con Pou.");
                    miPou.setHambre(miPou.getHambre() + 20);
                    miPou.setSuciedad(miPou.getSuciedad() + 20);
                    miPou.setEnergia(miPou.getEnergia() - 20);
                    miPou.setFelicidad(miPou.getFelicidad() + 15);
                    break;

                case 4:
                    System.out.println("\n💤 Pou durmió una siesta y recuperó energías.");
                    miPou.setEnergia(100);
                    miPou.setHambre(miPou.getHambre() + 10);
                    break;

                case 5:
                    System.out.println("\n❤️ Le hiciste unos mimos y se puso feliz.");
                    miPou.setFelicidad(miPou.getFelicidad() + 8);
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

            miPou.verificarLimites();
            db.guardarCambios(miPou);
        }

        db.desconectar();
        teclado.close();
    }
}