package com.lance.game.net;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lance
 * @since 2021/4/25
 */
@SpringBootTest(classes = MessageTest.Config.class)
public class MessageTest {



    @SpringBootApplication
    public static class Config {
    }
}
