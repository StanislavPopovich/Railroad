package com.railroad.exceptions;

/**
 * @author Stanislav Popovich
 */
public class RailroadDaoException extends Exception {

    public RailroadDaoException() {
    }

    public RailroadDaoException(String message) {
        super(message);
    }

    public RailroadDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RailroadDaoException(Throwable cause) {
        super(cause);
    }

    public RailroadDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
