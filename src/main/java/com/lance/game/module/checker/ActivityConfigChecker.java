package com.lance.game.module.checker;

import com.lance.common.tool.util.DateUtils;
import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.activity.manager.ActivityManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

/**
 * 活动配置校验器
 *
 * @author Lance
 * @since 2020/1/17 18:20
 */
//读数据库配置时，需要预加载所有的配置，启服非常耗性能。若不加载所有的配置到内存，不太推荐这样用
//@Component
public class ActivityConfigChecker implements IConfigChecker {

    @Resource
    private ActivityManager activityManager;

    @Override
    public void check() {
        Collection<ActivityConfig> activityConfigs = activityManager.getAllActivityConfig();
        for (ActivityConfig activityConfig : activityConfigs) {
            Date startDate = DateUtils.parse(activityConfig.getStartTime());
            Date stopDate = DateUtils.parse(activityConfig.getStopTime());
            if (startDate.getTime() >= stopDate.getTime()) {
                throw new IllegalArgumentException(
                        String.format("活动id=[%d]配置错误：开始时间[%s]大于或等于结束时间[%s]",
                                activityConfig.getId(), activityConfig.getStartTime(), activityConfig.getStopTime()));
            }
        }
    }
}
