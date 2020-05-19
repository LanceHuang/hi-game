package com.lance.game.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Lance
 */
public class NettyGameServer implements GameServer {

    // TODO 在线人数限制

    private EventLoopGroup boss   = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    /** 端口号 */
    private int port;

    @Override
    public void startup() {
        validate();

        ServerBootstrap b = new ServerBootstrap();
        b.group(boss, worker)
                .channel(ServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                    }
                })
                .bind();
    }

    /**
     * 配置校验
     */
    private void validate() {
        if (port <= 0) {
            throw new IllegalArgumentException("port not set");
        }
    }

    @Override
    public void shutdown() {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

}
