package com.lance.game.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lance
 */
public class BioGameServer implements GameServer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /** 端口号 */
    private int port;

    private ServerSocket serverSocket;

    /** 活跃连接数 */
    private final AtomicInteger activeCount = new AtomicInteger(0);

    private volatile boolean shutdown;

    @Override
    public void startup() {
        validate();

        try {
            this.serverSocket = new ServerSocket(this.port);
            logger.debug("启动游戏服，监听端口{}", this.port);

            while (!this.shutdown) {
                // 监听连接
                Socket socket = this.serverSocket.accept();
                int currActiveCount = this.activeCount.incrementAndGet();
                logger.debug("接收到新连接，当前连接数：{}", currActiveCount);

                // 分发任务
                dispatch(socket);
            }

            // 等待任务完成
            while (this.activeCount.get() > 0) {
                logger.debug("等待连接断开，当前连接数：{}", this.activeCount.get());
                Thread.sleep(1000L);
            }
            logger.debug("成功关闭游戏服");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(this.serverSocket);
        }
    }

    /**
     * 配置校验
     */
    private void validate() {
        if (this.port <= 0) {
            throw new IllegalArgumentException("port未配置");
        }
    }

    /**
     * 分发任务
     */
    private void dispatch(Socket socket) {
        new Thread(() -> {
//            try {
//                // 数据读取
//                while (true) {
//                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//
//                    // todo
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
            // 断开连接
            closeQuietly(socket);
//            }
            int currActiveCount = BioGameServer.this.activeCount.decrementAndGet();
            logger.debug("断开连接，当前连接数：{}", currActiveCount);
        }).start();
    }

    private void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            // do nothing
        }
    }

    @Override
    public void shutdown() {
        this.shutdown = true;
        logger.debug("开始关闭游戏服");
    }

    public void setPort(int port) {
        this.port = port;
    }
}
