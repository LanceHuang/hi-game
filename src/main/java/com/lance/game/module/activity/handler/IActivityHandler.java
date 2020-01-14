package com.lance.game.module.activity.handler;

import com.lance.game.module.activity.constant.ActivityType;
import com.lance.game.module.activity.model.ActivityInfo;

/**
 * 活动处理器
 *
 * @author Lance
 * @since 2019/9/5 20:41
 */
public interface IActivityHandler {

//    /**
//     * 判断活动能否开启
//     *
//     * @return {@code true} 可开启
//     */
//    boolean isCanOpen();

    /**
     * 活动类型
     */
    ActivityType getActivityType();

    /**
     * 开启活动
     */
    void start(ActivityInfo activityInfo);

    /**
     * 结束活动
     */
    void stop(ActivityInfo activityInfo);


    // todo 判断活动状态

    // todo 玩家能否参加活动
    // todo 玩家领取奖励？

}
