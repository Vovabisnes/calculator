import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class CalculatorReader {
    private final Scanner scanner;
    private final Pattern wrongSymbolPattern = Pattern.compile("[^+\\d \\-.*/]");
    private final Pattern numberAndOperatorPattern = Pattern.compile("\\d+\\.\\d+|\\d+|[+\\-*/]");

    public CalculatorReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<String> startReader() {
        ArrayList<String> expression = null;
        do {
            try {
                expression = parseLine(expressionInput());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        } while (expression == null);

        return expression;
    }

    private String expressionInput() {
        System.out.println("Enter your math expression");
        return scanner.nextLine();
    }

    private ArrayList<String> parseLine(String line) {

        if (containsWrongSymbol(line)) throw new IllegalArgumentException("Your expression contains wrong symbols");

        ArrayList<String> expression = getArrayList(line);

        if (isWrongExpression(expression) || hasWrongAmountOfSymbols(expression, line))
            throw new IllegalArgumentException("Your expression is incorrect");

        return expression;
    }

    private boolean containsWrongSymbol(String line) {
        return wrongSymbolPattern.matcher(line).find();
    }

    private ArrayList<String> getArrayList(String line) {
        Matcher matcher = numberAndOperatorPattern.matcher(line);
        ArrayList<String> expression = new ArrayList<>();
        while (matcher.find()) {
            expression.add(matcher.group());
        }
        return expression;
    }

    private boolean isWrongExpression(ArrayList<String> expression) {
        return hasNotRightSymbolsOrder(expression) || hasWrongOperatorAtTheBeginning(expression) || hasDivisionByZero(expression);
    }

    private boolean hasDivisionByZero(ArrayList<String> expression) {
        return IntStream.range(0, expression.size()).anyMatch(index -> expression.get(index).equals("/") &&
                expression.get(index + 1).equals("0"));
    }

    private boolean hasNotRightSymbolsOrder(ArrayList<String> expression) {
        if (isNumber(expression.get(0))) {
            return IntStream.range(1, expression.size()).anyMatch(symbolIndex ->
                    (symbolIndex % 2 != 0 && isNumber(expression.get(symbolIndex))) ||
                            symbolIndex % 2 == 0 && !isNumber(expression.get(symbolIndex)));
        } else {
            return IntStream.range(1, expression.size()).anyMatch(symbolIndex ->
                    (symbolIndex % 2 != 0 && !isNumber(expression.get(symbolIndex))) ||
                            symbolIndex % 2 == 0 && isNumber(expression.get(symbolIndex)));
        }
    }

    private boolean hasWrongOperatorAtTheBeginning(ArrayList<String> expression) {
        return !isNumber(expression.get(0)) && !expression.get(0).equals("-");
    }

    private boolean hasWrongAmountOfSymbols(ArrayList<String> expression, String line) {
        int amountOfSymbolsInExpression = expression.stream().mapToInt(String::length).sum();
        return (!isNumber(expression.get(0)) && expression.size() < 4) ||
                !isNumber(expression.get(expression.size() - 1)) ||
                amountOfSymbolsInExpression != line.replace(" ", "").length();
    }

    private boolean isNumber(String symbol) {
        if (symbol.startsWith("0")) {
            return !Pattern.compile("0\\d+").matcher(symbol).find();
        }
        return Pattern.compile("[^+-/*]").matcher(symbol).find();
    }
}
