package com.lance.game.net;

import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProtocolHandlerTest {

    private ProtocolContainer container;

    @BeforeEach
    public void BeforeEach() {
        container = new ProtocolContainer();
        container.registerHandler(10086, new TestRequestProtocolHandler());
    }

    @Test
    public void test() {
        TestRequest protocol = new TestRequest();
        protocol.setActivityId(1881);
        protocol.setValue(110L);

        ProtocolHandler handler = container.getHandler(10086);
        ByteBuf data = handler.serialize(protocol);
        TestRequest newProtocol = (TestRequest) handler.deserialize(data);
        assertEquals(protocol.getActivityId(), newProtocol.getActivityId());
        assertEquals(protocol.getValue(), newProtocol.getValue());
    }

}