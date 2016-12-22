package by.beregeiko.tuningshop.dao.exception;

/**
 * Created by Think on 09.12.2016.
 */
public class DaoSystemException extends DaoException {
    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
