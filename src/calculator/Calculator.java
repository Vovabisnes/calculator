package calculator;

import math.RPNParser;
import math.Validator;

import java.util.Scanner;

public class Calculator {
    private final Scanner scanner;

    public Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void calculate() {
        String[] terms = null;
        String input;

        do {
            if (terms != null) {
                System.out.println("Invalid input");
            }

            System.out.println("Enter your terms or <exit> (put gaps between terms and keep UPN): ");
            input = scanner.nextLine();

            terms = input.split(" ");
        } while (!Validator.isValid(terms));

        try {
            String result = RPNParser.calculateStack(terms);
            System.out.println(result);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
