package com.lance.game.module.common.util;

import com.lance.game.module.common.exception.GameException;

/**
 * 断言工具
 *
 * @author Lance
 * @since 2019/8/30 18:01
 */
public final class Assert {

    private Assert() {
    }

    public static void assertNotNull(Object obj, int i18nId) {
        if (null == obj) {
            throw new GameException(i18nId);
        }
    }

    public static void assertTrue(boolean flag, int i18nId) {
        if (flag) {
            throw new GameException(i18nId);
        }
    }

}
