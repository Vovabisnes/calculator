package reader;

import java.util.ArrayList;
import java.util.Scanner;

public class CalculatorReader {
    private final Scanner scanner;
    private final Validator validator;

    public CalculatorReader(Scanner scanner, Validator validator) {
        this.scanner = scanner;
        this.validator = validator;
    }

    public ArrayList<String> startReader() {
        ArrayList<String> expression = null;
        do {
            System.out.println("Enter your math expression");
            try {
                expression = validator.parseLine(scanner.nextLine());
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        } while (expression == null);

        return expression;
    }

}
