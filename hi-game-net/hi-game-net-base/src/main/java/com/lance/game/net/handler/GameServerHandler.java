package com.lance.game.net.handler;

import com.lance.game.net.message.MessageDefinition;
import com.lance.game.net.message.MessageManager;
import com.lance.game.net.session.Session;
import com.lance.game.net.session.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息服处理器
 *
 * @author Lance
 * @since 2021/4/9
 */
@ChannelHandler.Sharable
@Slf4j
public class GameServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Session session = SessionUtils.getSession(ctx.channel());

        // 判断是否有相应的处理器
        MessageDefinition messageDefinition = MessageManager.getInstance().getMessageDefinition(msg.getClass());
        if (messageDefinition == null || messageDefinition.getHandler() == null) {
            log.error("Unsupported message type: {}", msg.getClass().getName());
            return;
        }
        messageDefinition.getHandler().handle(session, msg);
    }
}
