package com.lance.game.mongodb.exception;

/**
 * 不支持数据类型
 *
 * @author Lance
 */
public class UnsupportedTypeException extends RuntimeException {

    public UnsupportedTypeException() {
    }

    public UnsupportedTypeException(String message) {
        super(message);
    }

    public UnsupportedTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
