package by.epam.corporate_education.service.exception;

public class EquivalencePasswordException extends ServiceException{
    private static final long serialVersionUID = -6903639085206048443L;

    public EquivalencePasswordException(String message) {
        super(message);
    }

    public EquivalencePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquivalencePasswordException(Throwable cause) {
        super(cause);
    }

    protected EquivalencePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
