package by.epam.corporate_education.dao.exception;

public class InvalidParameterTypeSortingException extends DAOException{
    private static final long serialVersionUID = -1768643854841294666L;

    public InvalidParameterTypeSortingException() {
        super();
    }

    public InvalidParameterTypeSortingException(String message) {
        super(message);
    }

    public InvalidParameterTypeSortingException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterTypeSortingException(Throwable cause) {
        super(cause);
    }

    protected InvalidParameterTypeSortingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
