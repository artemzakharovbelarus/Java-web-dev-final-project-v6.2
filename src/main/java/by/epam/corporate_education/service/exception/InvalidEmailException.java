package by.epam.corporate_education.service.exception;

public class InvalidEmailException extends ServiceException{
    private static final long serialVersionUID = -7893572642526366800L;

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmailException(Throwable cause) {
        super(cause);
    }

    protected InvalidEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
