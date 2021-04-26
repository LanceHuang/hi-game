package com.lance.game.net.handler;

import com.lance.game.net.codec.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 *
 * @author Lance
 * @since 2021/4/9
 */
public class MessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // todo in.readableBytes()

        Codec codec = Codec.getDefaultCodec();
        Object msg = codec.decode(in);
        out.add(msg);
    }
}
