import calculator.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Calculator calculator = new Calculator(new Scanner(System.in));
        calculator.start();
    }
}