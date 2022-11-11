import java.util.Scanner;

public class Reader {
    private double firstNumber;
    private double secondNumber;
    private String operator;
    private final Scanner scanner = new Scanner(System.in);

    public Reader() {
        readData();
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void readData(){
        System.out.println("Enter an operator( + - / * sqrt ): ");
        operator = readOperator();

        System.out.println("Enter the first number: ");
        while (true){
            firstNumber = readNumber();
            if (operator.equals("sqrt") && firstNumber<0){
                System.out.println("Enter the right number: ");
            } else if (operator.equals("sqrt")){
                return;
            } else {
                break;
            }
        }

        System.out.println("Enter the second number: ");
        while (true){
            secondNumber = readNumber();
            if (secondNumber==0 && operator.equals("/")){
                System.out.println("Division by 0 is impossible");
                System.out.println("Enter the right number");
            } else {
                break;
            }
        }
    }

    private String readOperator(){
        String operatorValue;
        while (true){
            operatorValue = scanner.next();
            if (operatorValue.equals("+") || operatorValue.equals("-") || operatorValue.equals("/") || operatorValue.equals("*") || operatorValue.equals("sqrt")) {
                return operatorValue;
            } else {
                System.out.println("Enter the right operator");
            }
        }
    }

    private double readNumber(){
        while (true) {
            String line = scanner.next();
            double number;
            try {
                number =  Double.parseDouble(line);
                return number;
            } catch (Exception e) {
                System.out.println("Enter the right number: ");
            }
        }
    }
}
