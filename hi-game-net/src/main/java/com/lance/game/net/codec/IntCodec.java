package com.lance.game.net.codec;

import com.lance.lim.net.util.ByteBufUtils;
import io.netty.buffer.ByteBuf;

/**
 * @author Lance
 * @since 2021/4/12
 */
public class IntCodec extends Codec {

    @Override
    public void encode(ByteBuf buf, Object object) {
        ByteBufUtils.writeInt(buf, (Integer) object);
    }

    @Override
    public Object decode(ByteBuf buf) {
        return ByteBufUtils.readInt(buf);
    }
}
