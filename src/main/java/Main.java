import calculator.Calculator;
import reader.CalculatorReader;
import reader.Validator;

import java.util.Scanner;

public class Main {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        CalculatorReader reader = new CalculatorReader(SCANNER, new Validator());
        Calculator calculator = new Calculator(reader);

        do {
            printTheResult(calculator.getResult());
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

    private static void printTheResult(double number) {
        String num = String.valueOf(number);
        if (num.charAt(num.length() - 2) == '.' && num.charAt(num.length() - 1) == '0') {
            System.out.printf("Your result is: %.0f\n", number);
        } else {
            System.out.println("Your result is: " + number + "\n");
        }
    }
}