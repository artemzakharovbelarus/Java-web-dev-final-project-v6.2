package by.epam.corporate_education.dao.exception;

public class NoUsernamePasswordException extends DAOException{
    private static final long serialVersionUID = -1089406674397275908L;

    public NoUsernamePasswordException() {
        super();
    }

    public NoUsernamePasswordException(String message) {
        super(message);
    }

    public NoUsernamePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUsernamePasswordException(Throwable cause) {
        super(cause);
    }

    protected NoUsernamePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
