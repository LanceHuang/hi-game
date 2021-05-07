package com.lance.game.net.schema;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;

/**
 * 消息schema
 *
 * @author Lance
 * @since 2021/4/28
 */
public abstract class MessageSchema {

    /**
     * 获取消息id
     *
     * @return 消息id
     */
    public abstract int getId();

    /**
     * 计算消息序列化大小
     *
     * @param obj 消息对象
     * @return 序列化大小
     */
    public abstract int getSerializedSize(Object obj);

    /**
     * 序列化消息
     *
     * @param obj 消息对象
     * @return 序列化数据
     * @throws IOException 序列化异常
     */
    public ByteBuf serialize(Object obj) throws IOException {
        ByteBuf buf = Unpooled.buffer(getSerializedSize(obj));
        serialize(buf, obj);
        return buf;
    }

    /**
     * 序列化消息
     *
     * @param buf 输出
     * @param obj 消息对象
     * @throws IOException 序列化异常
     */
    public abstract void serialize(ByteBuf buf, Object obj) throws IOException;

    /**
     * 反序列化消息
     *
     * @param buf 输入
     * @return 消息对象
     * @throws IOException 反序列化异常
     */
    public abstract Object deserialize(ByteBuf buf) throws IOException;
}
