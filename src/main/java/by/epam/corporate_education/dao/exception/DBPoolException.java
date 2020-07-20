package by.epam.corporate_education.dao.exception;

public class DBPoolException extends DAOException {
    private static final long serialVersionUID = 4944621070058280320L;

    public DBPoolException() {
        super();
    }

    public DBPoolException(String message) {
        super(message);
    }

    public DBPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBPoolException(Throwable cause) {
        super(cause);
    }

    protected DBPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
