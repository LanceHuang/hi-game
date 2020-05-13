package com.lance.game.orm.exception;

/**
 * 方法参数不正确异常
 *
 * @author Lance
 */
public class IncorrectMethodParameterCountException extends RuntimeException {

    public IncorrectMethodParameterCountException() {
    }

    public IncorrectMethodParameterCountException(String message) {
        super(message);
    }
}
