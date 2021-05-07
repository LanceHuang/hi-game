package com.lance.game.net.model;

import com.google.protobuf.CodedOutputStream;
import com.lance.game.net.schema.MessageSchema;
import com.lance.game.net.util.ByteBufUtils;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * @author Lance
 * @since 2021/4/28
 */
public class UserMessageSchema extends MessageSchema {

    @Override
    public int getId() {
        return 889;
    }

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
    public void serialize(ByteBuf buf, Object obj) throws IOException {
        User bean = (User) obj;
        ByteBufUtils.writeString(buf, bean.getUsername());
        ByteBufUtils.writeString(buf, bean.getPassword());
        ByteBufUtils.writeInt(buf, bean.getLevel());
    }

    @Override
    public Object deserialize(ByteBuf buf) throws IOException {
        User bean = new User();
        bean.setUsername(ByteBufUtils.readString(buf));
        bean.setPassword(ByteBufUtils.readString(buf));
        bean.setLevel(ByteBufUtils.readInt(buf));
        return bean;
    }
}
