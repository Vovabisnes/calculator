package exceptions;

public class WrongSymbolException extends Exception {
    @Override
    public String toString() {
        return "Your expression contains wrong symbols";
    }
}