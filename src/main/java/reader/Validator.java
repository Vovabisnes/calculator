package reader;

import exceptions.DivisionByZeroException;
import exceptions.WrongExpressionException;
import exceptions.WrongSymbolException;
import exceptions.WrongSymbolsOrderException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Validator {
    private static final Pattern WRONG_SYMBOL_PATTERN = Pattern.compile("[^+\\d \\-.*/]");
    private static final Pattern NUMBER_AND_OPERATOR_PATTERN = Pattern.compile("\\d+\\.\\d+|\\d+|[+\\-*/]");
    private static final Pattern WRONG_NUMBER_PATTERN = Pattern.compile("0\\d+");
    private static final Pattern NOT_OPERATOR_PATTERN = Pattern.compile("[^+-/*]");

    public List<String> parseLine(String line) throws Exception {
        if (containsWrongSymbol(line)) {
            throw new WrongSymbolException();
        }

        List<String> expression = getArrayList(line);

        if (hasWrongAmountOfSymbols(expression, line)) {
            throw new WrongExpressionException();
        }

        if (hasWrongSymbolsOrder(expression)) {
            throw new WrongSymbolsOrderException();
        }

        if (hasDivisionByZero(expression)) {
            throw new DivisionByZeroException();
        }

        return expression;
    }

    private boolean containsWrongSymbol(String line) {
        return WRONG_SYMBOL_PATTERN.matcher(line).find();
    }

    private List<String> getArrayList(String line) {
        Matcher matcher = NUMBER_AND_OPERATOR_PATTERN.matcher(line);
        List<String> expression = new ArrayList<>();
        while (matcher.find()) {
            expression.add(matcher.group());
        }
        return expression;
    }

    private boolean hasDivisionByZero(List<String> expression) {
        return IntStream.range(0, expression.size()).anyMatch(index -> expression.get(index).equals("/") &&
                expression.get(index + 1).equals("0"));
    }

    private boolean hasWrongSymbolsOrder(List<String> expression) {
        return IntStream.range(0, expression.size()).anyMatch(index ->
                !isNumber(expression.get(index)) && !isNumber(expression.get(index + 1)));
    }

    private boolean hasWrongAmountOfSymbols(List<String> expression, String line) {
        int amountOfSymbolsInExpression = expression.stream().mapToInt(String::length).sum();
        String replacedLine = line.replace(" ", "");
        boolean hasNotEnoughSymbols = amountOfSymbolsInExpression != replacedLine.length();
        boolean lastSymbolIsOperator = !isNumber(expression.get(expression.size() - 1));
        return hasNotEnoughSymbols || lastSymbolIsOperator;
    }

    private boolean isNumber(String symbol) {
        if (symbol.startsWith("0")) {
            return !WRONG_NUMBER_PATTERN.matcher(symbol).find();
        }
        return NOT_OPERATOR_PATTERN.matcher(symbol).find();
    }
}
