package com.lance.game.demo.exception;

import com.lance.game.demo.constant.I18nId;

/**
 * 游戏异常
 *
 * @author Lance
 * @since 2019/8/30 16:12
 */
public class GameException extends RuntimeException {

    /** 错误码 */
    private final int errorCode;

    public GameException() {
        this(I18nId.ERROR);
    }

    public GameException(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return getClass().getName() + ": errorCode=" + errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
