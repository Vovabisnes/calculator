import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private final Reader reader = new Reader();
    private final Scanner scanner = new Scanner(System.in);
    private int position;
    private boolean firstNumberIsNegative;

    public void start(){
        String line="";
        ArrayList<String> list;
        double result;

        while (!line.equals("n")) {
            position = 0;
            list = reader.startReader();
            result = calculate(list);
            if (result == -7777777 || position < list.size()){
                System.out.println("Your expression is not correct!");
                continue;
            } else {
                printTheResult(result);
            }
            System.out.println("\nContinue y/n?");
            line = scanner.next();
        }
    }

    private double calculate(ArrayList<String> expression){

        firstNumberIsNegative = expression.get(0).equals("-");

        try {
            double first = multiply(expression);
            if (first == -7777777){
                return -7777777;
            }
            while (position < expression.size()){
                String operator = expression.get(position);
                if (!operator.equals("+") && !operator.equals("-")){
                    break;
                } else {
                    position++;
                }
                double second = multiply(expression);
                if (second == -7777777){
                    return -7777777;
                }
                if(operator.equals("+")){
                    first+=second;
                } else {
                    first-=second;
                }
            }
            return first;
        } catch (Exception e){
            return -7777777;
        }
    }

    private double multiply(ArrayList<String> expression){
        try {
            double first;
            if (firstNumberIsNegative){
                expression.remove(expression.get(0));
                first = Double.parseDouble(expression.get(position++)) * -1;
                firstNumberIsNegative = false;
            } else {
                first = Double.parseDouble(expression.get(position++));
            }
            while (position < expression.size()){
                String operator = expression.get(position);
                if (!operator.equals("*") && !operator.equals("/")){
                    break;
                } else {
                    position++;
                }
                double second = Double.parseDouble(expression.get(position++));
                if(operator.equals("*")){
                    first*=second;
                } else if(second == 0) {
                    System.out.println("Division by 0 is not possible!");
                    return -7777777;
                } else {
                    first/=second;
                }
            }
            return first;
        } catch (Exception e){
            return -7777777;
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
