package com.lance.game.demo.util;

import com.lance.game.demo.exception.GameException;

/**
 * 断言工具，用于校验与抛异常，提高代码可读性
 *
 * @author Lance
 * @since 2020/12/1
 */
public class Assert {

    public static void isNull(Object obj, int errorCode) {
        if (obj != null) {
            throw new GameException(errorCode);
        }
    }

    public static void notNull(Object obj, int errorCode) {
        if (obj == null) {
            throw new GameException(errorCode);
        }
    }

    public static void isTrue(boolean value, int errorCode) {
        if (!value) {
            throw new GameException(errorCode);
        }
    }

    public static void isFalse(boolean value, int errorCode) {
        if (value) {
            throw new GameException(errorCode);
        }
    }

}
