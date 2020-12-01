package com.lance.game.demo.module.activity.service;

import com.lance.game.demo.module.activity.config.ActivityConfig;
import com.lance.game.demo.module.activity.handler.AbstractActivityHandler;
import com.lance.game.demo.module.activity.handler.IActivityHandler;
import com.lance.game.demo.module.activity.manager.ActivityManager;
import com.lance.game.demo.module.activity.model.ActivityInfo;
import com.lance.game.demo.core.log.LoggerUtil;
import com.lance.game.demo.module.activity.task.ActivityScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author Lance
 * @since 2019/9/5 22:33
 */
@Service
public class ActivityService implements IActivityService {

    @Resource
    private ActivityManager activityManager;

    @Resource
    private ActivityScheduler activityScheduler;

    private Map<Integer, ActivityInfo> activityInfos = new HashMap<>();

    /**
     * 活动初始化，这里不用{@link javax.annotation.PostConstruct}，是为了集中控制模块初始化顺序
     */
    @Override
    public void init() {
        LoggerUtil.debug("初始化活动");
        for (ActivityConfig activityConfig : activityManager.getAllActivityConfig()) {
            ActivityInfo activityInfo = ActivityInfo.valueOf(activityConfig);
            activityInfos.put(activityConfig.getId(), activityInfo);
        }

        LoggerUtil.debug("调度活动");
        for (Map.Entry<Integer, ActivityInfo> entry : activityInfos.entrySet()) {
            scheduleActivity(entry.getValue());
        }
    }

    /**
     * 调度活动
     *
     * @param activityInfo 活动信息
     */
    private void scheduleActivity(ActivityInfo activityInfo) {
        checkActivityHandler(activityInfo.getType());

        LoggerUtil.debug("活动调度 name【{}】, id【{}】", activityInfo.getName(), activityInfo.getId());
        if (activityInfo.isInOpenTime()) {
            LoggerUtil.debug("设置活动开启 name【{}】, id【{}】", activityInfo.getName(), activityInfo.getId());
            activityInfo.setActivityOpen();
        }

        long now = System.currentTimeMillis();
        if (now < activityInfo.getStartDate().getTime()) { // 活动开启定时器
            LoggerUtil.debug("创建活动开启定时器 name【{}】, id【{}】", activityInfo.getName(), activityInfo.getId());
            Future<?> startFuture = activityScheduler.schedule("活动开启定时器", new Runnable() {
                @Override
                public void run() {
                    startActivity(activityInfo);
                    activityInfo.setActivityOpen();
                }
            }, activityInfo.getStartDate());

            activityInfo.setStartFuture(startFuture);
        }
        if (now < activityInfo.getStopDate().getTime()) { // 活动关闭定时器
            LoggerUtil.debug("创建活动关闭定时器 name【{}】, id【{}】", activityInfo.getName(), activityInfo.getId());
            Future<?> stopFuture = activityScheduler.schedule("活动结束定时器", new Runnable() {
                @Override
                public void run() {
                    stopActivity(activityInfo);
                    activityInfo.setActivityClose();
                }
            }, activityInfo.getStopDate());

            activityInfo.setStopFuture(stopFuture);
        }
    }

    /**
     * 校验活动处理器
     *
     * @param type 活动类型
     */
    private void checkActivityHandler(int type) {
        IActivityHandler activityHandler = AbstractActivityHandler.getHandler(type);
        if (activityHandler == null) {
            throw new IllegalArgumentException("活动定时器不存在 type=" + type);
        }
    }

    @Override
    public void startActivity(ActivityInfo activityInfo) {
        IActivityHandler activityHandler = AbstractActivityHandler.getHandler(activityInfo.getType());
        activityHandler.start(activityInfo);
    }

    @Override
    public void stopActivity(ActivityInfo activityInfo) {
        IActivityHandler activityHandler = AbstractActivityHandler.getHandler(activityInfo.getType());
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
