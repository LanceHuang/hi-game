package com.lance.game.net.schema;

import com.lance.game.net.model.User;
import com.lance.game.net.model.UserMessageSchema;
import io.netty.buffer.ByteBuf;
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

        MessageSchema userSchema = new UserMessageSchema();
        ByteBuf buf = userSchema.serialize(user);
        System.out.println(Arrays.toString(buf.array()));
        for (byte b : buf.array()) {
            System.out.print((char) b);
            System.out.print(" ");
        }
        System.out.println();

        User newUser = (User) userSchema.deserialize(buf);
        System.out.println(newUser);
    }

}