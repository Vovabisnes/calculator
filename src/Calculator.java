import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private final CalculatorReader reader = new CalculatorReader();
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<String> expression;
    private int position;
    private boolean firstNumberIsNegative;

    public void start(){
        String line="";

        while (!line.equals("n")) {
            position = 0;
            expression = reader.startReader();
            printTheResult(calculate(expression));
            System.out.println("\nContinue y/n?");
            line = scanner.next();
        }
    }

    private double calculate(ArrayList<String> expression) {

        firstNumberIsNegative = expression.get(0).equals("-");

        double first = multiply(expression);

        while (position < expression.size()) {
            String operator = expression.get(position);
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                position++;
            }
            double second = multiply(expression);

            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }

    private double multiply(ArrayList<String> expression) {
        double first;
        if (firstNumberIsNegative) {
            expression.remove(expression.get(0));
            first = Double.parseDouble(expression.get(position++)) * -1;
            firstNumberIsNegative = false;
        } else {
            first = Double.parseDouble(expression.get(position++));
        }
        while (position < expression.size()) {
            String operator = expression.get(position);
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                position++;
            }
            double second = Double.parseDouble(expression.get(position++));
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }

    private void printTheResult(double number) {
        String num = String.valueOf(number);
        if (num.charAt(num.length() - 2) == '.' && num.charAt(num.length() - 1) == '0') {
            System.out.printf("Your result is: %.0f", number);
        } else {
            System.out.println("Your result is: " + number);
        }
    }
}
