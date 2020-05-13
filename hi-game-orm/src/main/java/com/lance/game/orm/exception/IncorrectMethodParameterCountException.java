package com.lance.game.orm.exception;

/**
 * 方法参数长度异常
 *
 * @author Lance
 */
public class IncorrectMethodParameterCountException extends RuntimeException {

    public IncorrectMethodParameterCountException() {
    }

    public IncorrectMethodParameterCountException(String message) {
        super(message);
    }

    public IncorrectMethodParameterCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
