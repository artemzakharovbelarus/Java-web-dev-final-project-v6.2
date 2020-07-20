package by.epam.corporate_education.service.exception;

public class InvalidSignUpValuesException extends ServiceException{
    private static final long serialVersionUID = 1599243035878964510L;

    public InvalidSignUpValuesException(String message) {
        super(message);
    }

    public InvalidSignUpValuesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSignUpValuesException(Throwable cause) {
        super(cause);
    }

    protected InvalidSignUpValuesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
