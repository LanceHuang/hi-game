package com.lance.game.demo;

/**
 * 游戏生命周期管理类
 *
 * @author Lance
 */
public interface IGameService {

    /**
     * 启动游戏，同时管理模块初始化顺序（先加载玩家数据，再加载其他数据）
     */
    void start();

    /**
     * 关闭游戏，关闭调用各模块的停服接口，关闭定时器
     */
    void stop();
}
