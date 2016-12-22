package by.beregeiko.tuningshop.dao.exception;

/**
 * Created by Think on 09.12.2016.
 */
public class DaoBusinessException extends DaoException {
    public DaoBusinessException(String message) {
        super(message);
    }

    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
