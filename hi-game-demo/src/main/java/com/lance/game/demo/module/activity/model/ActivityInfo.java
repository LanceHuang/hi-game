package com.lance.game.demo.module.activity.model;

import com.lance.game.demo.module.activity.config.ActivityConfig;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 活动信息
 *
 * @author Lance
 * @since 2019/9/6 10:28
 */
public class ActivityInfo {

    /** 活动配置 */
    private ActivityConfig activityConfig;

    /** 开始时间 */
    private Date startDate;
    /** 结束时间 */
    private Date stopDate;

    /** 活动状态 */
    private AtomicBoolean status;

    private Future<?> startFuture;
    private Future<?> stopFuture;
    // todo 活动进度
    // todo 领奖状态
    // todo 信息

    // todo 需不需要考虑活动循环开？先不考虑


    // todo 定时器信息

    // todo 到底要不要把ActivityConfig放这里面呢？ 生命周期是一样的，热更也要同时热更这个

    public static ActivityInfo valueOf(ActivityConfig activityConfig) {
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.activityConfig = activityConfig;
        activityInfo.status = new AtomicBoolean(false);
//        activityInfo.startDate = DateUtils.parse(activityConfig.getStartTime());
//        activityInfo.stopDate = DateUtils.parse(activityConfig.getStopTime());
        activityInfo.checkStartAndStopDate();
        return activityInfo;
    }

    /**
     * 活动时间校验
     */
    private void checkStartAndStopDate() {
        if (this.startDate.getTime() >= this.stopDate.getTime()) {
//            throw new IllegalArgumentException(
//                    String.format("活动id=[%d]配置错误：开始时间[%s]大于或等于结束时间[%s]",
//                            this.activityConfig.getId(), this.activityConfig.getStartTime(), this.activityConfig.getStopTime()));
        }
    }

    /**
     * 判断活动是否在开启时间内
     */
    public boolean isInOpenTime() {
        long now = System.currentTimeMillis();
        return this.startDate.getTime() <= now && now < this.stopDate.getTime();
    }

    /**
     * 开启活动
     */
    public void setActivityOpen() {
        this.status.set(true);
    }

    /**
     * 关闭活动
     */
    public void setActivityClose() {
        this.status.set(false);
    }

    //========================================================

    public int getId() {
        return this.activityConfig.getId();
    }

    public int getType() {
        return this.activityConfig.getType();
    }

    public String getName() {
        return this.activityConfig.getName();
    }

    public ActivityConfig getActivityConfig() {
        return activityConfig;
    }

    public void setActivityConfig(ActivityConfig activityConfig) {
        this.activityConfig = activityConfig;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public AtomicBoolean getStatus() {
        return status;
    }

    public void setStatus(AtomicBoolean status) {
        this.status = status;
    }

    public Future<?> getStartFuture() {
        return startFuture;
    }

    public void setStartFuture(Future<?> startFuture) {
        this.startFuture = startFuture;
    }

    public Future<?> getStopFuture() {
        return stopFuture;
    }

    public void setStopFuture(Future<?> stopFuture) {
        this.stopFuture = stopFuture;
    }
}
