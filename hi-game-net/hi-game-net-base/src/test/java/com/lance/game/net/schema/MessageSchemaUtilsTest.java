package com.lance.game.net.schema;

import com.lance.game.net.annotation.Message;
import com.lance.game.net.model.User;
import io.netty.buffer.ByteBuf;
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

        Message messageAnnotation = User.class.getAnnotation(Message.class);
        MessageSchema userSchema = MessageSchemaUtils.enhance(user.getClass(), messageAnnotation.value());
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