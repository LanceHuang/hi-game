package com.lance.game.net.handler;

import com.lance.game.net.message.MessageDefinition;
import com.lance.game.net.message.MessageManager;
import com.lance.game.net.util.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 *
 * @author Lance
 * @since 2021/4/9
 */
public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        MessageDefinition messageDefinition = MessageManager.getInstance().getMessageDefinition(msg.getClass());
        if (messageDefinition == null) {
            // todo 找不到消息定义
            return;
        }

        // 长度（int） 消息id（int） 数据
        byte[] body = messageDefinition.getSchema().serialize(msg);
        int bodyLen = body.length;
        int headerLen = computeRawVarint32Size(bodyLen);
        out.ensureWritable(headerLen + bodyLen);
        ByteBufUtils.writeInt(out, bodyLen); // todo zigzag?
        ByteBufUtils.writeBytes(out, body);
    }

    static int computeRawVarint32Size(final int value) {
        if ((value & (0xffffffff << 7)) == 0) {
            return 1;
        }
        if ((value & (0xffffffff << 14)) == 0) {
            return 2;
        }
        if ((value & (0xffffffff << 21)) == 0) {
            return 3;
        }
        if ((value & (0xffffffff << 28)) == 0) {
            return 4;
        }
        return 5;
    }
}
