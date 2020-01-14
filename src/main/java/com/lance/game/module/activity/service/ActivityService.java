package com.lance.game.module.activity.service;

import com.lance.common.tool.util.TimeUtils;
import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.activity.handler.AbstractActivityHandler;
import com.lance.game.module.activity.manager.ActivityManager;
import com.lance.game.module.activity.model.ActivityInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Lance
 * @since 2019/9/5 22:33
 */
@Service
public class ActivityService implements IActivityService {

    @Resource
    private ActivityManager activityManager;

    private Map<Integer, ActivityInfo> activityInfos;

    @Override
    public void init() {
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
//            activityInfo.setac
        }
        if (now < activityInfo.getStopDate().getTime()) { // 活动关闭定时器

        }
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
