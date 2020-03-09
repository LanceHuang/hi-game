package com.lance.game.util;

import com.lance.game.constant.I18nId;
import com.lance.game.exception.GameException;

import java.util.Collection;

/**
 * 断言工具
 *
 * @author Lance
 * @since 2019/8/30 18:01
 */
public class Assert {

    public static void assertNull(Object obj, int i18nId) {
        if (obj != null) {
            throw new GameException(i18nId);
        }
    }

    public static void assertNotNull(Object obj, int i18nId) {
        if (obj == null) {
            throw new GameException(i18nId);
        }
    }

    public static void assertNotNull(Object obj) {
        if (obj == null) {
            throw new GameException(I18nId.ERROR);
        }
    }

    public static void assertTrue(boolean value, int i18nId) {
        if (!value) {
            throw new GameException(i18nId);
        }
    }

    public static void assertFalse(boolean value, int i18nId) {
        if (value) {
            throw new GameException(i18nId);
        }
    }

    public static void assertGt(int num1, int num2, int i18nId) {
        if (num1 <= num2) {
            throw new GameException(i18nId);
        }
    }

    public static void assertGe(int num1, int num2, int i18nId) {
        if (num1 < num2) {
            throw new GameException(i18nId);
        }
    }

    public static void assertLt(int num1, int num2, int i18nId) {
        if (num1 > num2) {
            throw new GameException(i18nId);
        }
    }

    public static void assertLe(int num1, int num2, int i18nId) {
        if (num1 < num2) {
            throw new GameException(i18nId);
        }
    }

    public static void assertEquals(int num1, int num2, int i18nId) {
        if (num1 != num2) {
            throw new GameException(i18nId);
        }
    }

    public static void assertEquals(Object obj1, Object obj2, int i18nId) {
        if (obj1 == null && obj2 != null)
            throw new GameException(i18nId);
        if (obj1 == obj2)
            return;
        if (!obj1.equals(obj2))
            throw new GameException(i18nId);
    }

    public static void assertNotEmpty(Collection<?> c, int i18nId) {
        if (c == null || c.isEmpty()) {
            throw new GameException(i18nId);
        }
    }

    public static void assertNotEmpty(String str, int i18nId) {
        if (null == str || str.isEmpty()) {
            throw new GameException(i18nId);
        }
    }
}
