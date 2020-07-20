package by.epam.corporate_education.service.exception;

public class UserBannedException extends ServiceException{
    private static final long serialVersionUID = 3465795185705490981L;

    public UserBannedException(String message) {
        super(message);
    }

    public UserBannedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserBannedException(Throwable cause) {
        super(cause);
    }

    protected UserBannedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
