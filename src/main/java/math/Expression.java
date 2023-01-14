package math;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Expression implements Calculatable {
    @NonNull
    private final List<String> expression;
    private int position;
    private boolean firstNumberIsNegative;

    @Override
    public double calculate() {

        firstNumberIsNegative = expression.get(0).equals("-");

        double first = multiply();

        while (position < expression.size()) {
            String operator = expression.get(position);
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            }

            position++;
            double second = multiply();

            first = operator.equals("+") ? first + second : first - second;
        }
        return first;
    }

    @Override
    public double multiply() {
        double first;
        if (firstNumberIsNegative) {
            expression.remove(expression.get(0));
            first = Double.parseDouble(expression.get(position++)) * -1;
            firstNumberIsNegative = false;
        } else {
            first = Double.parseDouble(expression.get(position++));
        }
        while (position < expression.size()) {
            String operator = expression.get(position);
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            }

            position++;
            double second = Double.parseDouble(expression.get(position++));

            first = operator.equals("*") ? first * second : first / second;
        }
        return first;
    }
}
