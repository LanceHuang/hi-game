package com.lance.game.demo.module.activity.handler;

import com.lance.game.demo.module.activity.model.ActivityInfo;

/**
 * 活动处理器
 *
 * @author Lance
 * @since 2019/9/5 20:41
 */
public interface IActivityHandler {

    /**
     * 开启活动
     */
    void start(ActivityInfo activityInfo);

    /**
     * 关闭活动
     */
    void stop(ActivityInfo activityInfo);

}
