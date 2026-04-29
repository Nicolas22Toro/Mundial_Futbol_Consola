import java.util.Scanner;

public class ConsoleInput {
    private static Scanner sc = new Scanner(System.in);

    public static int readInt() {
        while (true) {
            try {
                String line = sc.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }

    public static String readString() {
        return sc.nextLine().trim();
    }
}
