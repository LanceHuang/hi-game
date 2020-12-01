package com.lance.game.demo.exception;

import com.lance.game.demo.constant.I18nId;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 */
class GameExceptionTest {

    @Test
    void test() {
        System.out.println(new GameException(I18nId.ERROR));
    }
}