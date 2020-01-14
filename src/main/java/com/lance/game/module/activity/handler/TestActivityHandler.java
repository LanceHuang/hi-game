package com.lance.game.module.activity.handler;

import com.lance.game.module.activity.constant.ActivityType;

/**
 * 测试活动
 *
 * @author Lance
 * @since 2020/1/14 12:40
 */
public class TestActivityHandler extends AbstractActivityHandler {

    @Override
    public ActivityType getActivityType() {
        return ActivityType.TEST;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
