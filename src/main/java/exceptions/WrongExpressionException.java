package exceptions;

public class WrongExpressionException extends Exception {
    @Override
    public String toString() {
        return "Your expression is incorrect";
    }
}