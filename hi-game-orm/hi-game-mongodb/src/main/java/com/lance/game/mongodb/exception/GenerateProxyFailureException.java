package com.lance.game.mongodb.exception;

/**
 * 生成代理类异常
 *
 * @author Lance
 */
public class GenerateProxyFailureException extends RuntimeException {

    public GenerateProxyFailureException() {
    }

    public GenerateProxyFailureException(String message) {
        super(message);
    }

    public GenerateProxyFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
