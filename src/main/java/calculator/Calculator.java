package calculator;

import math.Expression;
import reader.CalculatorReader;

public class Calculator {
    private final CalculatorReader reader;

    public Calculator(CalculatorReader reader) {
        this.reader = reader;
    }

    public double getResult() {
        Expression expression = new Expression(reader.startReader());
        return expression.calculate();
    }
}
