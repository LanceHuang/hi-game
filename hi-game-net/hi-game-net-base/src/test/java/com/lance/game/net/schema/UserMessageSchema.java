package com.lance.game.net.schema;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import java.io.IOException;

/**
 * @author Lance
 * @since 2021/4/28
 */
public class UserMessageSchema extends MessageSchema {

    @Override
    public int getSerializedSize(Object obj) {
        User bean = (User) obj;

        int size = 0;
        size += CodedOutputStream.computeStringSizeNoTag(bean.getUsername());
        size += CodedOutputStream.computeStringSizeNoTag(bean.getPassword());
        size += CodedOutputStream.computeInt32SizeNoTag(bean.getLevel());
        return size;
    }

    @Override
    public void serialize(CodedOutputStream output, Object obj) throws IOException {
        User bean = (User) obj;
        output.writeStringNoTag(bean.getUsername());
        output.writeStringNoTag(bean.getPassword());
        output.writeInt32NoTag(bean.getLevel());
    }

    @Override
    public Object deserialize(CodedInputStream input) throws IOException {
        User bean = new User();
        bean.setUsername(input.readStringRequireUtf8());
        bean.setPassword(input.readStringRequireUtf8());
        bean.setLevel(input.readInt32());
        return bean;
    }
}
