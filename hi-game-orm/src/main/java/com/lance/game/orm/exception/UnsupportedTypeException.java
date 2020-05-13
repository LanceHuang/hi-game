package com.lance.game.orm.exception;

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
}
