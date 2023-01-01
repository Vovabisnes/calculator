import calculator.Calculator;
import reader.CalculatorReader;
import reader.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator(new CalculatorReader(new Scanner(System.in), new Validator()));

        do {
            printTheResult(calculator.getResult());
        } while (readContinue(scanner));
    }

    private static boolean readContinue(Scanner scanner) {
        System.out.print("Continue? y/n: ");
        while (!scanner.hasNext("[yn]")) {
            scanner.nextLine();// Get rid of the old input
            System.out.print("Continue? y/n: ");
        }
        return "y".equals(scanner.nextLine());
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