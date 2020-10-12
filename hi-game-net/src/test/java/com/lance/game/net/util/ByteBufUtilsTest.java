package com.lance.game.net.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

public class ByteBufUtilsTest {

    @Test
    public void test() {
        ByteBuf buffer = Unpooled.buffer(1024);
        ByteBufUtils.writeInt(buffer, -1212454544);
        ByteBufUtils.writeInt(buffer, 10);
        System.out.println(buffer.capacity());
        System.out.println(buffer.readerIndex());
        System.out.println(buffer.writerIndex());
        System.out.println(ByteBufUtils.readInt(buffer));
        System.out.println(ByteBufUtils.readInt(buffer));
    }
}