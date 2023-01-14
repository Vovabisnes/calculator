package calculator;

import lombok.AllArgsConstructor;
import math.Expression;
import reader.CalculatorReader;

@AllArgsConstructor
public class Calculator {
    private final CalculatorReader reader;

    public double getResult() {
        Expression expression = new Expression(reader.startReader());
        return expression.calculate();
    }
}
