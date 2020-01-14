package com.lance.game.module.activity.manager;

import com.lance.game.module.activity.config.ActivityConfig;
import com.lance.game.module.activity.model.ActivityInfo;
import com.lance.game.module.config.IConfigReloadable;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Lance
 * @since 2020/1/14 14:25
 */
@Repository
public class ActivityManager implements IConfigReloadable {

    /** 活动配置 */
    private List<ActivityConfig> activityConfigs;

    private Map<Integer, ActivityInfo> idToInfo;

    private Map<Integer, ActivityInfo> typeToInfo;

    public void init() {
        for (ActivityConfig activityConfig : getAllActivityConfig()) {
            ActivityInfo activityInfo = ActivityInfo.valueOf(activityConfig);
            idToInfo.put(activityConfig.getId(), activityInfo);
            typeToInfo.put(activityConfig.getType(), activityInfo);
        }
    }

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

    public ActivityInfo getActivityInfoById(int id) {
        return this.idToInfo.get(id);
    }

    public ActivityInfo getActivityInfoByType(int type) {
        return this.typeToInfo.get(type);
    }

    @Override
    public void reload() {
        // todo
    }
}
