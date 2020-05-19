package com.lance.game.net;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class NewProtocolHandlerTest {

    @Test
    void test() throws IOException {
        TestRequest obj = new TestRequest();
        obj.setActivityId(568);
        obj.setAccount("Lance");
        obj.setValue(64644464L);

        NewProtocolHandler<TestRequest> protocolHandler = new TestRequestNewProtocolHandler();
        byte[] data = protocolHandler.serialize(obj);
        System.out.println(data.length); // int+long=12

        System.out.println(protocolHandler.deserialize(data));
    }

}