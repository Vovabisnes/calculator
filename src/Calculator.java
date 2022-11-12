import java.util.Scanner;

public class Calculator {
    private final Reader reader = new Reader();
    private final Scanner scanner = new Scanner(System.in);
    private Expression expression;

    public void start(){
        String line;
        do {
            expression = reader.setExpression();
            printTheResult(calculate());
            System.out.println("\nContinue y/n?");
            line = scanner.next();
        } while (!line.equals("n"));
    }
    private double calculate() {
        switch (expression.getOperator()) {
            case "+":
                return expression.getFirstNumber() + expression.getSecondNumber();
            case "-":
                return expression.getFirstNumber() - expression.getSecondNumber();
            case "*":
                return expression.getFirstNumber() * expression.getSecondNumber();
            case "/":
                return expression.getFirstNumber() / expression.getSecondNumber();
            default:
                return 0;
        }
    }

    private void printTheResult(double number){
        String num = String.valueOf(number);
        if (num.charAt(num.length()-2)=='.' && num.charAt(num.length()-1)=='0'){
            System.out.printf("Your result is: %.0f", number);
        } else {
            System.out.println("Your result is: " + number);
        }
    }
}
