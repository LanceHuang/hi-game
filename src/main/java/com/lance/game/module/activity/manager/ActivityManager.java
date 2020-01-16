package com.lance.game.module.activity.manager;

import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.config.IConfigReloadable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lance
 * @since 2020/1/14 14:25
 */
@Repository
public class ActivityManager implements IConfigReloadable {

    /** 活动配置 */
    private List<ActivityConfig> activityConfigs;

    public Collection<ActivityConfig> getAllActivityConfig() {
        return Collections.unmodifiableCollection(this.activityConfigs);
    }

    public ActivityConfig getActivityConfigById(int id) {
        return this.activityConfigs.get(id);
    }

    public Collection<ActivityConfig> getActivityConfigsByType(int type) {
        List<ActivityConfig> result = new LinkedList<>();
        for (ActivityConfig activityConfig : this.activityConfigs) {
            if (activityConfig.getType() == type) {
                result.add(activityConfig);
            }
        }
        return result;
    }

    @Override
    public void reload() {
        // todo 活动怎么热更？
        // todo 需要判断活动类型，以及活动状态，判断活动当时是否支持热更
    }

}
