package com.lance.game.net.handler;

import com.lance.game.net.message.MessageDefinition;
import com.lance.game.net.message.MessageManager;
import com.lance.game.net.util.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 解码器
 *
 * @author Lance
 * @since 2021/4/9
 */
@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        int preIndex = in.readerIndex();
        int length = ByteBufUtils.readInt(in);
        if (preIndex == in.readerIndex()) {
            return;
        }
        if (length < 0) {
            throw new IllegalArgumentException("negative length: " + length);
        }

        if (in.readableBytes() < length) {
            in.resetReaderIndex();
        } else {
            ByteBuf data = in.readRetainedSlice(length);
            Object msg = decodeMessage(data);
            if (msg != null) {
                out.add(msg);
            }
        }
    }

    /**
     * 消息解码
     */
    private Object decodeMessage(ByteBuf data) throws Exception {
        // 解析消息id
        data.markReaderIndex();
        int messageId = ByteBufUtils.readInt(data);
        data.resetReaderIndex();
        MessageDefinition messageDefinition = MessageManager.getInstance().getMessageDefinition(messageId);
        if (messageDefinition == null) {
            log.error("Unsupported message id: {}", messageId);
            return null;
        }

        // 解析消息
        return messageDefinition.getSchema().deserialize(data);
    }
}
