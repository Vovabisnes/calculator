import calculator.Calculator;

import java.util.Scanner;

public class Main {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Calculator calculator = new Calculator(SCANNER);
        do {
            calculator.calculate();
        } while (readContinue());
    }

    private static boolean readContinue() {
        System.out.print("Continue? y/n: ");
        while (!SCANNER.hasNext("[yn]")) {
            SCANNER.nextLine();
            System.out.print("Continue? y/n: ");
        }
        return "y".equals(SCANNER.nextLine());
    }
}