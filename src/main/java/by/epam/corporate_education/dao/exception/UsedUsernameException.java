package by.epam.corporate_education.dao.exception;

public class UsedUsernameException extends DAOException{
    private static final long serialVersionUID = -254281786363036978L;

    public UsedUsernameException(String message) {
        super(message);
    }

    public UsedUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsedUsernameException(Throwable cause) {
        super(cause);
    }

    protected UsedUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
