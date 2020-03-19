package com.lance.game.module.activity.model;

import com.lance.game.module.activity.constant.ActivityRegion;

/**
 * 活动时间配置
 *
 * @author Lance
 * @since 2020/2/24 12:43
 */
public class ActivityTimeConfig {

    private ActivityRegion region;

    private String value;

    public ActivityRegion getRegion() {
        return region;
    }

    public void setRegion(ActivityRegion region) {
        this.region = region;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
