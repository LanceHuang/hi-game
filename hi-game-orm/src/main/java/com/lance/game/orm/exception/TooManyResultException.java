package com.lance.game.orm.exception;

/**
 * 太多返回值异常
 *
 * @author Lance
 */
public class TooManyResultException extends RuntimeException {

    public TooManyResultException(String message) {
        super(message);
    }
}
