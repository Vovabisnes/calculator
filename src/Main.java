public class Main {
    public static void main(String[] args) {
        double result;
        Calculator calculator = new Calculator();

        calculator.dataInput();
        result = calculator.calculate();
        calculator.printTheResult(result);
    }
}