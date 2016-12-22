package by.beregeiko.tuningshop.dao.exception;

/**
 * Created by Think on 09.12.2016.
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
