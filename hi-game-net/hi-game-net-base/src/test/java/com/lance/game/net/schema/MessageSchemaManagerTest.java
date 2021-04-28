package com.lance.game.net.schema;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Lance
 * @since 2021/4/28
 */
@SpringBootTest(classes = MessageSchemaManagerTest.Config.class)
public class MessageSchemaManagerTest {

    @Autowired
    private MessageSchemaManager messageSchemaManager;

    @Test
    public void test() throws IOException {
        User user = new User();
        user.setUsername("Lance");
        user.setPassword("123456");
        user.setLevel(128);

        MessageSchema userSchema = messageSchemaManager.getSchema(user.getClass());
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
    public static class Config {

        @Bean
        public MessageSchemaManager messageSchemaManager() {
            return new MessageSchemaManager();
        }
    }
}