package com.lance.game.module.activity.handler;

/**
 * 活动处理器
 *
 * @author Lance
 * @since 2019/9/5 20:41
 */
public interface IActivityHandler {

    /**
     * 初始化
     */
    void init();

    /**
     * 开启活动
     */
    void start();

    /**
     * 结束活动
     */
    void stop();

}
