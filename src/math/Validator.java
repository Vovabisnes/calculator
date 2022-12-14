package math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean isValid(String[] inputTerms){
        for (String term: inputTerms){
            if (!isNumber(term) && !isOperator(term)){
                return false;
            }
        }
        return true;
    }

    public static boolean isNumber(String input){
        Pattern numberPattern = Pattern.compile("\\d+\\.\\d+|\\d+");
        Matcher matcher = numberPattern.matcher(input);

        if (input.length() > 1 && input.charAt(0) == '0' ) return false;
        return matcher.find() && matcher.group().length() == input.length();
    }

    public static boolean isOperator(String input){
        Pattern operatorPattern = Pattern.compile("[+\\-*/]");
        Matcher matcher = operatorPattern.matcher(input);

        return matcher.find() && input.length() == 1;
    }
}
