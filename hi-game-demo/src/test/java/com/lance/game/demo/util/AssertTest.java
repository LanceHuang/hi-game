package com.lance.game.demo.util;

import com.lance.game.demo.constant.I18nId;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2020/12/1
 */
class AssertTest {

    @Test
    void test() {
        String message = "Hello world";
        Assert.notNull(message, I18nId.ERROR);
    }

    @Test
    void testException() {
        Assert.notNull(null, I18nId.ERROR);
    }
}