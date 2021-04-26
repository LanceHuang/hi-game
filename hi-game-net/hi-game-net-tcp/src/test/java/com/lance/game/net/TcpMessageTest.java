package com.lance.game.net;

import com.lance.game.net.config.EnableTcpMessage;
import com.lance.game.net.config.TcpMessageProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lance
 * @since 2021/4/26
 */
@SpringBootTest(classes = TcpMessageTest.Config.class)
public class TcpMessageTest {

    @Autowired
    private TcpMessageProperties properties;

    @Test
    public void test() {
        System.out.println(properties);
    }

    @SpringBootApplication
    @EnableTcpMessage
    public static class Config {
    }
}
