package com.lance.game.net;

/**
 * 游戏服务器
 *
 * @author Lance
 */
public interface GameServer {

    /**
     * 启服
     */
    void startup();

    /**
     * 停服
     */
    void shutdown();
}
