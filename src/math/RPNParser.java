package math;

import stacks.Stack;

public class RPNParser {
    public static String calculateStack(String[] terms) {
        Stack stack = new Stack(terms.length);

        for (String term : terms) {
            if (Validator.isNumber(term)) {
                stack.push(term);
            } else if (Validator.isOperator(term)) {
                final String right = stack.pop();
                final String left = stack.pop();
                if ((term.equals("/") && (right.equals("0.0") || right.equals("0")))) {
                    throw new IllegalArgumentException("Division by 0 is impossible");
                }
                final String result = String.valueOf(compute(left, right, term));
                stack.push(result);
            }
        }
        return stack.pop();
    }

    private static double compute(String left, String right, String operator) {
        double a = Double.parseDouble(left);
        double b = Double.parseDouble(right);

        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "/":
                return a / b;
            case "*":
                return a * b;
            default:
                throw new IllegalArgumentException("wrong operator " + operator);
        }
    }
}
