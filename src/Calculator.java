public class Calculator {
    private final Reader reader = new Reader();
    public double calculate() {
        switch (reader.getOperator()) {
            case "+":
                return reader.getFirstNumber() + reader.getSecondNumber();
            case "-":
                return reader.getFirstNumber() - reader.getSecondNumber();
            case "*":
                return reader.getFirstNumber() * reader.getSecondNumber();
            case "/":
                return reader.getFirstNumber() / reader.getSecondNumber();
        }
        return 0;
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
