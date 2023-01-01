package exceptions;

public class DivisionByZeroException extends Exception {
    @Override
    public String toString() {
        return "Division by 0 is not possible";
    }
}
