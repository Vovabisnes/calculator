import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private final Reader reader = new Reader();
    private final Scanner scanner = new Scanner(System.in);
    private int position = 0;

    public void start(){
        String line="";
        ArrayList<String> list;
        double result;

        while (!line.equals("n")) {
            list = reader.startReader();
            result = calculate(list);
            if (result == -7777777){
                System.out.println("Your expression is not correct!");
                continue;
            } else {
                printTheResult(result);
                position = 0;
            }
            System.out.println("\nContinue y/n?");
            line = scanner.next();
        }
    }

    private double calculate(ArrayList<String> expression){

        boolean firstNumberIsNegative = expression.get(0).equals("-");

        try {
            double first;
            if (firstNumberIsNegative){
                expression.remove(expression.get(0));
                first = Double.parseDouble(expression.get(position++)) * -1;
            } else {
                first = Double.parseDouble(expression.get(position++));
            }
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
                double second = Double.parseDouble(expression.get(position++));
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
            System.out.println("something went wrong");
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
