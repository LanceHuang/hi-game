package com.lance.game.mongodb.exception;

/**
 * 太多返回值异常
 *
 * @author Lance
 */
public class TooManyResultException extends RuntimeException {

    public TooManyResultException() {
    }

    public TooManyResultException(String message) {
        super(message);
    }

    public TooManyResultException(String message, Throwable cause) {
        super(message, cause);
    }
}
