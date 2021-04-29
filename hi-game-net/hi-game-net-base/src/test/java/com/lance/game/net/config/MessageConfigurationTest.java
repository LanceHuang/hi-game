package com.lance.game.net.config;

import com.lance.game.net.message.MessageDefinition;
import com.lance.game.net.message.MessageManager;
import com.lance.game.net.message.EnableMessage;
import com.lance.game.net.schema.MessageSchema;
import com.lance.game.net.schema.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Lance
 * @since 2021/4/29
 */
@SpringBootTest(classes = MessageConfigurationTest.Config.class)
public class MessageConfigurationTest {

    @Autowired
    private MessageManager messageManager;

    @Test
    public void test() throws IOException {
        User user = new User();
        user.setUsername("Lance");
        user.setPassword("123456");
        user.setLevel(128);

        MessageDefinition messageDefinition = messageManager.getMessageDefinition(user.getClass());
        MessageSchema userSchema = messageDefinition.getSchema();
        byte[] data = userSchema.serialize(user);
        System.out.println(Arrays.toString(data));

        for (byte b : data) {
            System.out.print((char) b);
            System.out.print(" ");
        }

        User newUser = (User) userSchema.deserialize(data);
        System.out.println(newUser);
    }

    @SpringBootApplication
    @EnableMessage
    public static class Config {
    }
}