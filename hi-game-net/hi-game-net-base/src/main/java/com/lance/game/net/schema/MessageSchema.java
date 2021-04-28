package com.lance.game.net.schema;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import java.io.IOException;

/**
 * 消息schema
 *
 * @author Lance
 * @since 2021/4/28
 */
public abstract class MessageSchema {

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
    public byte[] serialize(Object obj) throws IOException {
        byte[] result = new byte[getSerializedSize(obj)];
        CodedOutputStream output = CodedOutputStream.newInstance(result);
        serialize(output, obj);
        return result;
    }

    /**
     * 序列化消息
     *
     * @param output 输出
     * @param obj    消息对象
     * @throws IOException 序列化异常
     */
    public abstract void serialize(CodedOutputStream output, Object obj) throws IOException;

    /**
     * 反序列化消息
     *
     * @param data 序列化数据
     * @return 消息对象
     * @throws IOException 反序列化异常
     */
    public Object deserialize(byte[] data) throws IOException {
        CodedInputStream input = CodedInputStream.newInstance(data);
        return deserialize(input);
    }

    /**
     * 反序列化消息
     *
     * @param input 输入
     * @return 消息对象
     * @throws IOException 反序列化异常
     */
    public abstract Object deserialize(CodedInputStream input) throws IOException;
}
