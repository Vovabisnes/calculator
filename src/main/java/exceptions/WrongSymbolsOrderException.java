package exceptions;

public class WrongSymbolsOrderException extends Exception {
    @Override
    public String toString() {
        return "Your expression has wrong symbols order";
    }
}
