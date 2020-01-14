package com.lance.game.module.activity.model;

import com.lance.game.module.activity.config.ActivityConfig;
import lombok.Data;

/**
 * 活动信息
 *
 * @author Lance
 * @since 2019/9/6 10:28
 */
@Data
public class ActivityInfo {

    // todo 活动时间
    // todo 活动进度
    // todo 领奖状态
    // todo 信息

    // todo 需不需要考虑活动循环开？

    private int id;
    private int type;

    public static ActivityInfo valueOf(ActivityConfig activityConfig) {
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.id = activityConfig.getId();
        activityInfo.type = activityConfig.getType();
        return activityInfo;
    }



}
