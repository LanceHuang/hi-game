package com.lance.game.module.activity.model;

import com.lance.common.tool.util.DateUtils;
import com.lance.common.tool.util.TimeUtils;
import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.activity.constant.ActivityConstant;
import com.lance.game.module.activity.constant.ActivityStatus;
import com.lance.game.module.activity.constant.ActivityType;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 活动信息
 *
 * @author Lance
 * @since 2019/9/6 10:28
 */
@Data
public class ActivityInfo {

    /** 活动配置 */
    private ActivityConfig activityConfig;

    /** 开启时间 */
    private Date          startDate;
    /** 结束时间 */
    private Date          stopDate;
    /** 活动状态 */
    private AtomicBoolean status;

    // todo 活动进度
    // todo 领奖状态
    // todo 信息

    // todo 需不需要考虑活动循环开？先不考虑


    // todo 定时器信息

    // todo 到底要不要把ActivityConfig放这里面呢？ 生命周期是一样的，热更也要同时热更这个

    public static ActivityInfo valueOf(ActivityConfig activityConfig) {
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.activityConfig = activityConfig;
        activityInfo.status = new AtomicBoolean(ActivityConstant.STATUS_ACTIVITY_CLOSE);
        return activityInfo;
    }

    /**
     * 初始化活动时间
     */
    public void initActivityDate() {
        this.startDate = DateUtils.parse(activityConfig.getStartTime());
        this.stopDate = DateUtils.parse(activityConfig.getStopTime());
    }

    /**
     * 判断是否在开启时间内
     */
    public boolean isInOpenTime() {
        long now = TimeUtils.now();
        return startDate.getTime() <= now && now < stopDate.getTime();
    }

    /**
     * 开启活动
     */
    public void setActivityOpen() {
        this.status.set(ActivityConstant.STATUS_ACTIVITY_OPEN);
    }

    /**
     * 关闭活动
     */
    public void setActivityClose() {
        this.status.set(ActivityConstant.STATUS_ACTIVITY_CLOSE);
    }

    //========================================================

    public int getId() {
        return this.activityConfig.getId();
    }

    public int getType() {
        return this.activityConfig.getType();
    }

}
