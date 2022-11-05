public class Expression {
    private final double firstNumber;
    private final double secondNumber;
    private final String operator;

    public Expression(double firstNumber, double secondNumber, String operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
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
        }
        return 0;
    }
}
