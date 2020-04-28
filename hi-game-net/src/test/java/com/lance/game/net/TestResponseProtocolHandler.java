package com.lance.game.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Lance
 */
public class TestResponseProtocolHandler implements ProtocolHandler {

    @Override
    public Class<?> getClazz() {
        return TestResponse.class;
    }

    @Override
    public ByteBuf serialize(Object obj) {
        TestResponse protocol = (TestResponse) obj;
        ByteBuf byteBuf = Unpooled.buffer(); // todo
        return byteBuf;
    }

    @Override
    public Object deserialize(ByteBuf byteBuf) {
        TestResponse protocol = new TestResponse();
        // todo
        return protocol;
    }
}
