package by.epam.corporate_education.service.exception;

public class InvalidPasswordException extends ServiceException{
    private static final long serialVersionUID = -8413494327399196026L;

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordException(Throwable cause) {
        super(cause);
    }

    protected InvalidPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
