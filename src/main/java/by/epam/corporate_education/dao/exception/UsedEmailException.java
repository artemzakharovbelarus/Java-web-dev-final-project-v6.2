package by.epam.corporate_education.dao.exception;

public class UsedEmailException extends DAOException{
    private static final long serialVersionUID = -5446206596714629855L;

    public UsedEmailException(String message) {
        super(message);
    }

    public UsedEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsedEmailException(Throwable cause) {
        super(cause);
    }

    protected UsedEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
