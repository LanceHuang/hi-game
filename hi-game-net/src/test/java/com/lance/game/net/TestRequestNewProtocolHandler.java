package com.lance.game.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Lance
 */
public class TestRequestNewProtocolHandler implements NewProtocolHandler<TestRequest> {

    @Override
    public byte[] serialize(TestRequest obj) throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteOut);

        out.writeInt(obj.getActivityId());
        out.writeLong(obj.getValue());

        return byteOut.toByteArray();
    }

    @Override
    public TestRequest deserialize(byte[] data) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(data));

        TestRequest obj = new TestRequest();
        obj.setActivityId(in.readInt());
        obj.setValue(in.readLong());
        return obj;
    }

}
