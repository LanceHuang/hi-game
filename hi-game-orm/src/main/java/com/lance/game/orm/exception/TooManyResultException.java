package com.lance.game.orm.exception;

/**
 * @author Lance
 */
public class TooManyResultException extends RuntimeException {

    public TooManyResultException(String message) {
        super(message);
    }
}
