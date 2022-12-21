import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorReader {
    private final Scanner scanner;

    public CalculatorReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<String> startReader() {
        while (true) {
            ArrayList<String> expression = parseLine(expressionInput());
            if (expression == null) {
                System.out.println("Your expression is not correct");
            } else {
                return expression;
            }
        }
    }

    private String expressionInput() {
        System.out.println("Enter your math expression");
        return scanner.nextLine();
    }

    private ArrayList<String> parseLine(String line) {

        if (Pattern.compile("[^+\\d \\-.*/]").matcher(line).find()) {
            return null;
        }

        Matcher matcher2 = Pattern.compile("\\d+\\.\\d+|\\d+|[+\\-*/]").matcher(line);
        ArrayList<String> expression = new ArrayList<>();
        while (matcher2.find()) {
            expression.add(matcher2.group());
        }

        line = line.replace(" ", "");
        if (line.length() != countSymbols(expression) || hasDivisionByZero(expression) || !hasRightSymbolsOrder(expression))
            return null;

        return expression;
    }

    private int countSymbols(ArrayList<String> expression) {
        return expression.stream().mapToInt(String::length).sum();
    }

    private boolean hasRightSymbolsOrder(ArrayList<String> expression) {
        if (expression.isEmpty()) {
            return false;
        } else {
            if (!isNumber(expression.get(0)) && !expression.get(0).equals("-")) {
                return false;
            } else if (isNumber(expression.get(0))) {
                for (int i = 1; i < expression.size(); i++) {
                    if ((i % 2 != 0 && isNumber(expression.get(i)) || (i % 2 == 0 && !isNumber(expression.get(i))))) {
                        return false;
                    }
                }
            } else if (!isNumber(expression.get(0))) {
                if (expression.size() == 2 && isNumber(expression.get(1))) return true;

                if (expression.size() < 4) {
                    return false;
                }
                for (int i = 1; i < expression.size(); i++) {
                    if ((i % 2 != 0 && !isNumber(expression.get(i)) || (i % 2 == 0 && isNumber(expression.get(i))))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private boolean isNumber(String line) {
        return Pattern.compile("[^+-/*]").matcher(line).find();
    }

    private boolean hasDivisionByZero(ArrayList<String> expression) {
        for (int i = 0; i < expression.size(); i++) {
            if (expression.get(i).equals("/") && expression.get(i + 1).equals("0"))
                return true;
        }
        return false;
    }
}
