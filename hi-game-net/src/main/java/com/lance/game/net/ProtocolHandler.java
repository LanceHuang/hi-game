package com.lance.game.net;

import io.netty.buffer.ByteBuf;

/**
 * 协议处理器
 *
 * @author Lance
 */
public interface ProtocolHandler {

    /**
     * 处理类
     */
    Class<?> getClazz();

    /**
     * 将对象转换成数据
     */
    ByteBuf serialize(Object obj);

    /**
     * 将数据转换成对象
     */
    Object deserialize(ByteBuf byteBuf);
}
