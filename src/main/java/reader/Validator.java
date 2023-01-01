package reader;

import exceptions.DivisionByZeroException;
import exceptions.WrongExpressionException;
import exceptions.WrongSymbolException;
import exceptions.WrongSymbolsOrderException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Validator {
    private final Pattern wrongSymbolPattern = Pattern.compile("[^+\\d \\-.*/]");
    private final Pattern numberAndOperatorPattern = Pattern.compile("\\d+\\.\\d+|\\d+|[+\\-*/]");
    private final Pattern wrongNumberPattern = Pattern.compile("0\\d+");
    private final Pattern notOperatorPattern = Pattern.compile("[^+-/*]");

    public ArrayList<String> parseLine(String line) throws Exception {
        if (containsWrongSymbol(line)) throw new WrongSymbolException();

        ArrayList<String> expression = getArrayList(line);

        if (hasWrongAmountOfSymbols(expression, line)) throw new WrongExpressionException();

        if (hasWrongSymbolsOrder(expression)) throw new WrongSymbolsOrderException();

        if (hasDivisionByZero(expression)) throw new DivisionByZeroException();

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

    private boolean hasDivisionByZero(ArrayList<String> expression) {
        return IntStream.range(0, expression.size()).anyMatch(index -> expression.get(index).equals("/") &&
                expression.get(index + 1).equals("0"));
    }

    private boolean hasWrongSymbolsOrder(ArrayList<String> expression) {
        return IntStream.range(0, expression.size()).anyMatch(index ->
                !isNumber(expression.get(index)) && !isNumber(expression.get(index + 1)));
    }

    private boolean hasWrongAmountOfSymbols(ArrayList<String> expression, String line) {
        int amountOfSymbolsInExpression = expression.stream().mapToInt(String::length).sum();
        return amountOfSymbolsInExpression != line.replace(" ", "").length() ||
                !isNumber(expression.get(expression.size() - 1));
    }

    private boolean isNumber(String symbol) {
        if (symbol.startsWith("0")) {
            return !wrongNumberPattern.matcher(symbol).find();
        }
        return notOperatorPattern.matcher(symbol).find();
    }
}
