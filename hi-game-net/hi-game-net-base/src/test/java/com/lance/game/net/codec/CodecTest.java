package com.lance.game.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2021/4/26
 */
public class CodecTest {

    @Test
    public void test() {
        ByteBuf buf = Unpooled.buffer(1024);

        Codec codec = Codec.getCodec(int.class);
        codec.encode(buf, null);
        System.out.println(codec.decode(buf));
    }
}