package com.lance.game.orm.exception;

/**
 * 类解析异常
 *
 * @author Lance
 */
public class ResolveClassFailureException extends RuntimeException {

    public ResolveClassFailureException() {
    }

    public ResolveClassFailureException(String message) {
        super(message);
    }

    public ResolveClassFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
