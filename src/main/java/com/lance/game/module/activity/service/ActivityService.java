package com.lance.game.module.activity.service;

import com.lance.common.tool.util.TimeUtils;
import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.activity.handler.AbstractActivityHandler;
import com.lance.game.module.activity.handler.IActivityHandler;
import com.lance.game.module.activity.manager.ActivityManager;
import com.lance.game.module.activity.model.ActivityInfo;
import com.lance.net.util.NamedThreadFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Lance
 * @since 2019/9/5 22:33
 */
@Service
public class ActivityService implements IActivityService {

    @Resource
    private ActivityManager activityManager;

    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors();

    private ScheduledExecutorService scheduledExecutorService;

    private Map<Integer, ActivityInfo> activityInfos;

    @Override
    public void init() {
        scheduledExecutorService = Executors.newScheduledThreadPool(CORE_SIZE, new NamedThreadFactory("activity"));

        for (ActivityConfig activityConfig : activityManager.getAllActivityConfig()) {
            ActivityInfo activityInfo = ActivityInfo.valueOf(activityConfig);
            initActivity(activityInfo);
            activityInfos.put(activityConfig.getId(), activityInfo);
        }
    }

    @Override
    public void initActivity(ActivityInfo activityInfo) {
        /*
        若还未开启，则创建定时器
        若正在开启，设置状态位
        若已结束，跳过
        还要考虑复用问题
         */

        activityInfo.initActivityDate();
        if (activityInfo.isInOpenTime()) {
            activityInfo.setActivityOpen();
        }

        long now = TimeUtils.now();
        if (now < activityInfo.getStartDate().getTime()) { // 活动开启定时器
            Future<?> startFuture = schedule(new Runnable() {
                @Override
                public void run() {
                    startActivity(activityInfo);
                }
            }, activityInfo.getStartDate());

            activityInfo.setStartFuture(startFuture);
        }
        if (now < activityInfo.getStopDate().getTime()) { // 活动关闭定时器
            Future<?> stopFuture = schedule(new Runnable() {
                @Override
                public void run() {
                    stopActivity(activityInfo);
                }
            }, activityInfo.getStopDate());

            activityInfo.setStopFuture(stopFuture);
        }
    }

    private Future<?> schedule(Runnable task, Date date) {
        return scheduledExecutorService.schedule(task, date.getTime() - TimeUtils.now(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void startActivity(ActivityInfo activityInfo) {
        IActivityHandler activityHandler = AbstractActivityHandler.getActivityHandler(activityInfo.getType());
        activityHandler.start(activityInfo);
    }

    @Override
    public void stopActivity(ActivityInfo activityInfo) {
        IActivityHandler activityHandler = AbstractActivityHandler.getActivityHandler(activityInfo.getType());
        activityHandler.stop(activityInfo);
    }

    /**
     * 获取开启中的活动（同一时间同一类型的活动，只会开启一个）
     *
     * @param type 活动类型
     */
    public ActivityInfo getOpenActivityByType(int type) {
        // todo
        return null;
    }

    public ActivityInfo getActivityInfoById(int id) {
        return this.activityInfos.get(id);
    }

}
