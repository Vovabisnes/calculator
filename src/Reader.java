import java.util.Scanner;

public class Reader {
    private final Scanner scanner = new Scanner(System.in);

    public Expression setExpression (){
        while (true){
            Expression exp = parseLine(expressionInput());
            if(exp != null){
                return exp;
            }
        }
    }

    private String expressionInput(){
        System.out.println("Enter your math expression");
        return scanner.next();
    }

    private Expression parseLine(String line){
        boolean isFirstSymbolMinus = false;
        if (line.charAt(0)=='-'){
            isFirstSymbolMinus = true;
            line = line.substring(1);
        }

        try {
            int operatorIndex;

            if (line.contains("+")) {
                operatorIndex = line.indexOf("+");
            } else if (line.contains("-")){
                operatorIndex = line.indexOf("-");
            } else if (line.contains("/")){
                operatorIndex = line.indexOf("/");
            } else if (line.contains("*")) {
                operatorIndex = line.indexOf("*");
            } else return null;

                try {
                    String operator = String.valueOf(line.charAt(operatorIndex));
                    double firstNumber = Double.parseDouble(line.substring(0,operatorIndex));
                    if (isFirstSymbolMinus) firstNumber *= -1;
                    double secondNumber = Double.parseDouble(line.substring(operatorIndex+1));

                    if (secondNumber==0 && operator.equals("/")) {
                        System.out.println("Division by 0 is not possible");
                        return null;
                    }

                    return new Expression(firstNumber, secondNumber, operator);
                } catch (Exception e) {
                    return null;
                }
        } catch (Exception e) {
            return null;
        }
    }
}
