package by.beregeiko.tuningshop.dao.exception;

/**
 * Created by Think on 09.12.2016.
 */
public class NoSuchEntityException extends DaoBusinessException {
    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
