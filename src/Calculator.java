import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private static final ArrayList<String> operators = new ArrayList<>();

    static {
        operators.add("+");
        operators.add("-");
    }
   public void start(){
        while (true){
            dataInput();
        }
    }

    private void dataInput(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> expression;

        System.out.println("Enter your math expression: ");
        while (true){
            String line = scanner.nextLine();
            expression = parseLine(line);
            double result = calculate(expression);

            if (expression == null || result == -1234567891){
                System.out.println("Enter the right expression: ");
            } else {
                printTheResult(result);
                break;
            }
        }
    }

    private ArrayList<String> parseLine(String line){
        line = line.replace(" ", "");

        ArrayList<String> expression = new ArrayList<>();
        int currentOperatorIndex;
        int previousOperatorIndex = 0;
        int nextOperatorIndex;

        while (true){
            try {
                currentOperatorIndex = getNextOperatorIndex(line,previousOperatorIndex);

                expression.add(line.substring(previousOperatorIndex, currentOperatorIndex));
                expression.add(String.valueOf(line.charAt(currentOperatorIndex)));

                nextOperatorIndex = getNextOperatorIndex(line,currentOperatorIndex);
                if (nextOperatorIndex == -1){
                    expression.add(line.substring(currentOperatorIndex+1));
                    break;
                }
                previousOperatorIndex = currentOperatorIndex+1;
            }  catch (Exception e){
                return null;
            }
        }
        return expression;
    }

    public static double calculate(ArrayList<String> expression){
        int position = 0;
        try {
            double first = Double.parseDouble(expression.get(position++));
            while (position < expression.size()){
                String operator = expression.get(position);
                if (!operator.equals("+") && !operator.equals("-")){
                    break;
                } else {
                    position++;
                }
                double second = Double.parseDouble(expression.get(position++));
                if(operator.equals("+")){
                    first+=second;
                } else {
                    first-=second;
                }
            }
            return first;
        } catch (Exception e){
            return -1234567891;
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

    private int getNextOperatorIndex(String line, int index){

       for (int i =index+1; i<line.length(); i++){
           if (operators.contains(String.valueOf(line.charAt(i)))){
               return i;
           }
       }
       return -1;
    }

}
