package calculator;

import math.RPNParser;
import math.Validator;

import java.util.Scanner;

public class Calculator {
    private final Scanner scanner;

    public Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() throws IllegalAccessException {
        String[] terms;
        String input;

        while (true) {
            terms = null;

            do {
                if (terms != null) {
                    System.out.println("Invalid input");
                }

                System.out.println("Enter your terms or <exit> (put gaps between terms and keep UPN): ");
                input = scanner.nextLine();
                if (input.equals("exit")) {
                    break;
                }

                terms = input.split(" ");
            } while (!Validator.isValid(terms));

            if (input.equals("exit")) {
                break;
            }

            try {
                String result = RPNParser.calculateStack(terms);
                System.out.println(result);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                start();
            }
        }
    }
}
