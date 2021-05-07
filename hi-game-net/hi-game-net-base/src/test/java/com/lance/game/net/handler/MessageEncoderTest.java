package com.lance.game.net.handler;

import com.lance.game.net.message.EnableMessage;
import com.lance.game.net.model.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lance
 * @since 2021/5/7
 */
@SpringBootTest(classes = MessageEncoderTest.Config.class)
public class MessageEncoderTest {

    @Test
    public void test() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                new MessageEncoder(),
                new MessageDecoder()
        );

        // 写出
        User user = new User();
        user.setUsername("Lance");
        user.setPassword("123456");
        user.setLevel(10085);
        System.out.println(user);
        embeddedChannel.writeOutbound(user);

        ByteBuf data = embeddedChannel.readOutbound();
        System.out.println(data.readableBytes());
        int readableBytes = data.readableBytes();
        for (int i = 0; i < readableBytes; i++) {
            System.out.print(data.getByte(i));
            System.out.print(" ");
        }
        System.out.println();

        // 写入
        embeddedChannel.writeInbound(data);
        User newUser = embeddedChannel.readInbound();
        System.out.println(newUser);
    }

    @SpringBootApplication
    @EnableMessage("com.lance.game.net")
    public static class Config {
    }
}