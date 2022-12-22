package math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean isValid(String[] inputTerms){
        int numberCounter = 0;
        int operatorCounter = 0;

        for (String term : inputTerms) {
            if (!isNumber(term) && !isOperator(term)) {
                return false;
            }
            if (isNumber(term)) {
                numberCounter++;
            } else if (isOperator(term)) {
                if (numberCounter < 2 || operatorCounter + 1 >= numberCounter) {
                    return false;
                }
                operatorCounter++;
            }
        }
        return (numberCounter == operatorCounter + 1);
    }

    public static boolean isNumber(String input){
        Pattern numberPattern = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher matcher = numberPattern.matcher(input);

        if (input.length() > 1 && input.charAt(0) == '0' && !input.contains(".")) return false;
        return matcher.find() && matcher.group().length() == input.length();
    }

    public static boolean isOperator(String input){
        Pattern operatorPattern = Pattern.compile("[+\\-/*]");
        Matcher matcher = operatorPattern.matcher(input);

        return matcher.find() && input.length() == 1;
    }
}
