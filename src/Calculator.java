import java.util.Scanner;

public class Calculator {
    private final Reader reader = new Reader();

    public void start(){
        Scanner scanner = new Scanner(System.in);
        String line;
        while (true){
            printTheResult(calculate());
            System.out.println("\nContinue y/n?");
            line = scanner.next();
            if (line.equals("n")) break;
            reader.readData();
        }
    }
    private double calculate() {
        switch (reader.getOperator()) {
            case "+":
                return reader.getFirstNumber() + reader.getSecondNumber();
            case "-":
                return reader.getFirstNumber() - reader.getSecondNumber();
            case "*":
                return reader.getFirstNumber() * reader.getSecondNumber();
            case "/":
                return reader.getFirstNumber() / reader.getSecondNumber();
            case "sqrt":
                return Math.sqrt(reader.getFirstNumber());
        }
        return 0;
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
