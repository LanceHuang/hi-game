package com.lance.game.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Lance
 */
public class TestRequestProtocolHandler implements ProtocolHandler {

    @Override
    public Class<?> getClazz() {
        return TestRequest.class;
    }

    @Override
    public ByteBuf serialize(Object obj) {
        TestRequest protocol = (TestRequest) obj;
        ByteBuf byteBuf = Unpooled.buffer(); // todo
        byteBuf.writeInt(protocol.getActivityId());
//        byteBuf todo account
        byteBuf.writeLong(protocol.getValue());
        return byteBuf;
    }

    @Override
    public Object deserialize(ByteBuf byteBuf) {
        TestRequest protocol = new TestRequest();
        protocol.setActivityId(byteBuf.readInt());
//        protocol.setAccount(byteBuf.readInt()); // todo
        protocol.setValue(byteBuf.readLong());
        return protocol;
    }
}
