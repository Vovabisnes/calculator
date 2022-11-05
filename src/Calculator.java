import java.util.Scanner;

public class Calculator {
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public void dataInput(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an operator: ");
        while (true){
            operator = scanner.next();
            if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") || operator.equals("sqrt")) {
                break;
            } else {
                System.out.println("Enter the right operator");
            }
        }

        System.out.println("Enter the first number: ");
        while (true) {
            String line = scanner.next();
            try {
                firstNumber = Double.parseDouble(line);
                break;
            } catch (Exception e) {
                System.out.println("Enter the right number: ");
            }
        }

        if (operator.equals("sqrt")) {
            return;
        }

        System.out.println("Enter the second number: ");
        while (true) {
            String line = scanner.next();
            if (line.equals("0")) {
                System.out.println("Division by 0 is not possible");
                System.out.println("Enter the right number: ");
                continue;
            }
            try {
                secondNumber = Double.parseDouble(line);
                break;
            } catch (Exception e) {
                System.out.println("Enter the right number: ");
            }
        }
    }

    public double calculate() {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "/":
                return firstNumber / secondNumber;
            case "*":
                return firstNumber * secondNumber;
            default:
                return Math.sqrt(firstNumber);
        }
    }

    public void printTheResult(double number){
        String num = String.valueOf(number);
        if (num.charAt(num.length()-2)=='.' && num.charAt(num.length()-1)=='0'){
            System.out.printf("Your result is: %.0f", number);
        } else {
            System.out.println("Your result is: " + number);
        }
    }

}
