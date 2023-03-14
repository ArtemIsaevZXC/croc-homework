import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private final static String[] UNITS = {"B", "KB", "MB", "GB", "TB", "PB", "EB"};
    private final static String MESSAGE = "Введите целое неотрицательное число, не превышающее Long.MAX_VALUE.";

    private static void printBytes(long bytes) {
        if ( bytes >= 0 ) {
            byte unitCounter = 0;
            double result = bytes;
            while (result >= 1024) {
                result /= 1024;
                unitCounter++;
            }
            System.out.println(String.format(Locale.US, "%.1f %s", result, UNITS[unitCounter]));
        }
        else {
            System.out.println(MESSAGE);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Для завершения программы введите пустую строку.\n" + MESSAGE);
        String input = sc.nextLine();
        // Условие остановки цикла.
        while (!input.equals("")) {
            // Проверка корректности входных данных.
            if (input.matches("^\\d+$")
                    && (new BigDecimal(input).compareTo(new BigDecimal(Long.MAX_VALUE)) <= 0)) {
                printBytes(Long.parseLong(input));
                input = sc.nextLine();
            } else {
                System.out.println(MESSAGE);
                input = sc.nextLine();
            }
        }
    }
}