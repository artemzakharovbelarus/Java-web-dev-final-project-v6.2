package by.epam.corporate_education.service.exception;

public class InvalidSignInValuesException extends ServiceException{
    private static final long serialVersionUID = 3884779803750789362L;

    public InvalidSignInValuesException(String message) {
        super(message);
    }

    public InvalidSignInValuesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSignInValuesException(Throwable cause) {
        super(cause);
    }

    protected InvalidSignInValuesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
