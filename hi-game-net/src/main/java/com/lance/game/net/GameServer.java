package com.lance.game.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;

/**
 * 游戏服务器
 *
 * @author Lance
 */
public class GameServer {

    // TODO 在线人数限制

    private EventLoopGroup boss   = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    /** 端口号 */
    private int port;

    /**
     * 启动
     */
    public void start() {
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

    /**
     * 关闭
     */
    public void stop() {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

}
