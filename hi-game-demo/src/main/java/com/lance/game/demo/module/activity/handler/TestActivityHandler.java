package com.lance.game.demo.module.activity.handler;

import com.lance.game.demo.module.activity.constant.ActivityType;
import com.lance.game.demo.module.activity.model.ActivityInfo;
import org.springframework.stereotype.Component;

/**
 * 测试活动
 *
 * @author Lance
 * @since 2020/1/14 12:40
 */
@Component
public class TestActivityHandler extends AbstractActivityHandler {

    @Override
    public ActivityType getActivityType() {
        return ActivityType.TEST;
    }

    @Override
    public void start(ActivityInfo activityInfo) {
        System.out.println("开启活动");
    }

    @Override
    public void stop(ActivityInfo activityInfo) {
        System.out.println("关闭活动");
    }
}
