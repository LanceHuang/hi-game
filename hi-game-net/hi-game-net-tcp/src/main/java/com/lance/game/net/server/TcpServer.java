package com.lance.game.net.server;

import com.lance.game.common.util.ComponentLifecycle;
import com.lance.game.net.config.TcpMessageProperties;
import com.lance.game.net.handler.MessageDecoder;
import com.lance.game.net.handler.MessageEncoder;
import com.lance.game.net.handler.GameServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author Lance
 * @since 2021/4/26
 */
@Slf4j
public class TcpServer extends ComponentLifecycle {

    private final TcpMessageProperties properties;

    public TcpServer(TcpMessageProperties properties) {
        this.properties = properties;
    }

    private EventLoopGroup boss;

    private EventLoopGroup worker;

    @Override
    public void doStart() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // todo ssl
                        ch.pipeline().addLast(new LoggingHandler());
                        // todo 心跳校验
//                        ch.pipeline().addLast(new IdleStateHandler(s, 0, 0));
                        ch.pipeline().addLast(new MessageEncoder());
                        ch.pipeline().addLast(new MessageDecoder());
                        // todo ?
                        ch.pipeline().addLast(new GameServerHandler());
                    }
                });

        try {
            b.bind(properties.getPort()).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();
        log.info("TcpServer startup in {} ms", stopWatch.getLastTaskTimeMillis());
    }

    @Override
    public void doStop() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            if (boss != null) {
                boss.shutdownGracefully().sync();
            }
            if (worker != null) {
                worker.shutdownGracefully().sync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();
        log.info("TcpServer shutdown in {} ms", stopWatch.getLastTaskTimeMillis());
    }
}

