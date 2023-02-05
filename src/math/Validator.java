package math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+\\.\\d+|\\d+");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("[+\\-/*]");

    public static boolean isValid(String[] inputTerms) {
        int amountOfNumbers = 0;
        int amountOfOperators = 0;

        for (String term : inputTerms) {
            if (!isNumber(term) && !isOperator(term)) {
                return false;
            }
            if (isNumber(term)) {
                amountOfNumbers++;
            } else if (isOperator(term)) {
                if (amountOfNumbers < 2 || amountOfOperators + 1 >= amountOfNumbers) {
                    return false;
                }
                amountOfOperators++;
            }
        }
        return (amountOfNumbers == amountOfOperators + 1);
    }

    public static boolean isNumber(String input) {
        if (input.length() > 1 && input.startsWith("0") && input.charAt(1) != '.') {
            return false;
        }

        Matcher matcher = NUMBER_PATTERN.matcher(input);
        return matcher.find() && matcher.group().length() == input.length();
    }

    public static boolean isOperator(String input) {
        Matcher matcher = OPERATOR_PATTERN.matcher(input);
        return matcher.find() && input.length() == 1;
    }
}
