package com.lance.game.net;

import com.lance.game.net.config.EnableMessage;
import com.lance.game.net.config.MessageProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lance
 * @since 2021/4/25
 */
@SpringBootTest(classes = MessageTest.Config.class)
public class MessageTest {

    @Autowired
    private MessageProperties properties;

    @Test
    public void test() {
        System.out.println(properties);
    }

    @SpringBootApplication
    @EnableMessage
    public static class Config {
    }
}
