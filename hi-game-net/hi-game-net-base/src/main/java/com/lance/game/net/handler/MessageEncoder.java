package com.lance.game.net.handler;

import com.lance.game.net.codec.Codec;
import com.lance.game.net.util.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器：长度（int） 消息id（int） 数据
 *
 * @author Lance
 * @since 2021/4/9
 */
public class MessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        // 占位
        out.markWriterIndex();
        ByteBufUtils.writeInt(out, 0);

        // 写入数据
        int startWriterIndex = out.writerIndex();
        Codec codec = Codec.getDefaultCodec();
        codec.encode(out, msg);
        int endWriterIndex = out.writerIndex();

        // 写入长度
        out.resetWriterIndex();
        int length = endWriterIndex - startWriterIndex;
        ByteBufUtils.writeInt(out, length);
        out.writerIndex(endWriterIndex);
    }
}
