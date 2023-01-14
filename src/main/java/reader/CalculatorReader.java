package reader;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class CalculatorReader {
    private final Scanner scanner;
    private final Validator validator;

    public List<String> startReader() {
        List<String> expression = null;
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
