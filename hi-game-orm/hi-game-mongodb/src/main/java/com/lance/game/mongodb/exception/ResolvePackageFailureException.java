package com.lance.game.mongodb.exception;

/**
 * 包解析异常
 *
 * @author Lance
 */
public class ResolvePackageFailureException extends RuntimeException {

    public ResolvePackageFailureException() {
    }

    public ResolvePackageFailureException(String message) {
        super(message);
    }

    public ResolvePackageFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
