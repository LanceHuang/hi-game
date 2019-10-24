package com.lance.game.module.activity.handler;

/**
 * 活动处理器
 *
 * @author Lance
 * @since 2019/9/5 20:41
 */
public interface IActivityHandler {

    /**
     * 初始化活动
     */
    void init();

//    /**
//     * 判断活动能否开启
//     *
//     * @return {@code true} 可开启
//     */
//    boolean isCanOpen();

    /**
     * 开启活动
     */
    void start();

    /**
     * 结束活动
     */
    void stop();


    // todo 判断活动状态

    // todo 玩家能否参加活动
    // todo 玩家领取奖励？

}
