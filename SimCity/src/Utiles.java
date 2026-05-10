import java.util.Scanner;

public class Utiles {
    private static Scanner sc = new Scanner(System.in);

    public static int verificarEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
    }

    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }
}