package exception;

public class InvalidCalculateInputException extends Exception {
    public InvalidCalculateInputException() {
        super("Time or Cost invalid (not positive)!");
    }
}
