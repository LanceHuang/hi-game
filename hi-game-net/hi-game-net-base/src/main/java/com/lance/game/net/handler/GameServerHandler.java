package com.lance.game.net.handler;

import com.lance.game.net.executor.MessageDispatcher;
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

    private MessageDispatcher dispatcher;

    public GameServerHandler(MessageDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg == null) {
            return;
        }

        Session session = SessionUtils.getSession(ctx.channel());
        dispatcher.dispatch(session, msg);
    }
}
