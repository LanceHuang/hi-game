package com.lance.game.net;

import java.io.IOException;

/**
 * 协议处理器
 *
 * @author Lance
 */
public interface NewProtocolHandler<T> {

    /**
     * 将对象转换成数据
     */
    byte[] serialize(T obj) throws IOException;

    /**
     * 将数据转换成对象
     */
    T deserialize(byte[] data) throws IOException;
}
