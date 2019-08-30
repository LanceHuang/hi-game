package com.lance.game.module.common.exception;

/**
 * 游戏异常
 *
 * @author Lance
 * @since 2019/8/30 16:12
 */
public class GameException extends RuntimeException {

    /** 错误码 */
    private int errorCode;

    public GameException(int errorCode) {
        super("errorCode=" + errorCode);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
