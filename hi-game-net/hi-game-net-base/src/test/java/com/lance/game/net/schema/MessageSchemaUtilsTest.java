package com.lance.game.net.schema;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Lance
 * @since 2021/4/28
 */
public class MessageSchemaUtilsTest {

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setUsername("Lance");
        user.setPassword("123456");
        user.setLevel(128);

        MessageSchema userSchema = MessageSchemaUtils.enhance(user.getClass());
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