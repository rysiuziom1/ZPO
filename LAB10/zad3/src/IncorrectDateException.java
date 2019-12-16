public class IncorrectDateException extends Exception {
    public IncorrectDateException(String errorMessage) {
        super(errorMessage);
    }

    public IncorrectDateException() {
        super();
    }
}
