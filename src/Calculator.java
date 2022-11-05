import java.util.Scanner;

public class Calculator {
     private Expression expression;

   public void start(){
        while (true){
            dataInput();
            printTheResult(expression.calculate());
        }
    }

    private void dataInput(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your math expression: ");
        while (true){
            String line = scanner.nextLine();
            expression = parseLine(line);
            if(expression == null){
                System.out.println("Enter the right expression: ");
            } else {
                break;
            }
        }
    }

    private Expression parseLine(String line){
        line = line.replace(" ", "");
        try {
            int operatorIndex = -1;

            if (line.contains("+")) {
                operatorIndex = line.indexOf("+");
            } else if (line.contains("-")){
                operatorIndex = line.indexOf("-");
            } else if (line.contains("/")){
                operatorIndex = line.indexOf("/");
            } else if (line.contains("*")) {
                operatorIndex = line.indexOf("*");
            }

            if (operatorIndex == -1){
                return null;
            } else {
                try {
                    String operator = String.valueOf(line.charAt(operatorIndex));
                    double firstNumber = Double.parseDouble(line.substring(0,operatorIndex));
                    double secondNumber = Double.parseDouble(line.substring(operatorIndex+1));
                    
                    if (secondNumber==0 && operator.equals("/")) return null;

                    return new Expression(firstNumber, secondNumber, operator);
                } catch (Exception e) {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
    }

    private void printTheResult(double number){
        String num = String.valueOf(number);
        if (num.charAt(num.length()-2)=='.' && num.charAt(num.length()-1)=='0'){
            System.out.printf("Your result is: %.0f\n\n", number);
        } else {
            System.out.println("Your result is: " + number);
        }
    }

}
