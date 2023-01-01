package math;

import java.util.ArrayList;

public class Expression implements Calculate {
    private final ArrayList<String> expression;
    private int position;
    private boolean firstNumberIsNegative;

    public Expression(ArrayList<String> expression) {
        this.expression = expression;
    }

    @Override
    public double calculate() {

        firstNumberIsNegative = expression.get(0).equals("-");

        double first = multiply();

        while (position < expression.size()) {
            String operator = expression.get(position);
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                position++;
            }
            double second = multiply();

            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
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
            } else {
                position++;
            }
            double second = Double.parseDouble(expression.get(position++));
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }
}
