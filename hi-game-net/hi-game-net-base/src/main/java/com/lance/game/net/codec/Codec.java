package com.lance.game.net.codec;

import io.netty.buffer.ByteBuf;

/**
 * 消息编码解码器
 *
 * @author Lance
 * @since 2021/4/12
 */
public abstract class Codec {

    public static Codec getCodec(Class<?> clazz) {
        return CodecRegistry.getCodec(clazz);
    }

    public static Codec getDefaultCodec() {
        return CodecRegistry.getDefaultCodec();
    }

    /**
     * 编码
     *
     * @param buf    字节缓冲区
     * @param object 编码对象
     */
    public abstract void encode(ByteBuf buf, Object object);

    /**
     * 解码
     *
     * @param buf 字节缓冲区
     * @return 解码对象
     */
    public abstract Object decode(ByteBuf buf);
}
