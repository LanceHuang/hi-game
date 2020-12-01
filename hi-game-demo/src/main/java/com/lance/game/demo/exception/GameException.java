package com.lance.game.demo.exception;

import com.lance.game.demo.constant.I18nId;
import lombok.Getter;

/**
 * 游戏异常
 *
 * @author Lance
 * @since 2019/8/30 16:12
 */
@Getter
public class GameException extends RuntimeException {

    /** 错误码（捕获到该异常后，可获得错误码，返回相应的协议给客户端） */
    private int errorCode;

    public GameException() {
        this(I18nId.ERROR);
    }

    public GameException(int errorCode) {
        super("errorCode=" + errorCode);
        this.errorCode = errorCode;
    }
}
