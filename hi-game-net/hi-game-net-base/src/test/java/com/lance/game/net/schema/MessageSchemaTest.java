package com.lance.game.net.schema;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Lance
 * @since 2021/4/28
 */
public class MessageSchemaTest {

    @Test
    public void test() throws IOException {
        User user = new User();
        user.setUsername("Lance");
        user.setPassword("123456");
        user.setLevel(128);

//        MessageSchema userSchema = MessageSchema.getSchema(User.class);
        MessageSchema userSchema = new UserMessageSchema();
        byte[] data = userSchema.serialize(user);
        System.out.println(Arrays.toString(data));

        for (byte b : data) {
            System.out.print((char) b);
            System.out.print(" ");
        }

        User newUser = (User) userSchema.deserialize(data);
        System.out.println(newUser);
    }

}