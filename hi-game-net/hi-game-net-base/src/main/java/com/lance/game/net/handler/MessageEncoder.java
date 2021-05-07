package com.lance.game.net.handler;

import com.lance.game.net.message.MessageDefinition;
import com.lance.game.net.message.MessageManager;
import com.lance.game.net.util.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 编码器
 *
 * @author Lance
 * @since 2021/4/9
 */
@Slf4j
public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (msg == null) {
            return;
        }

        MessageDefinition messageDefinition = MessageManager.getInstance().getMessageDefinition(msg.getClass());
        if (messageDefinition == null) {
            log.error("Unsupported message type: {}", msg.getClass().getName());
            return;
        }

        ByteBuf body = messageDefinition.getSchema().serialize(msg);
        int bodyLen = body.readableBytes();
        int headerLen = ByteBufUtils.computeInt(bodyLen);
        out.ensureWritable(headerLen + bodyLen);
        ByteBufUtils.writeInt(out, bodyLen);
        out.writeBytes(body);
    }
}
