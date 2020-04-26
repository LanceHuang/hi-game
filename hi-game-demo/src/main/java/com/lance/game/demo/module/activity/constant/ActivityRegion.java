package com.lance.game.demo.module.activity.constant;

/**
 * 活动渠道：QQ空间、微信小游戏（雷同于AFK的先锋服、国际服、国服）
 *
 * @author Lance
 * @since 2020/2/24 12:45
 */
public enum ActivityRegion {

    ALL("ALL"),
    QQ("QQ");

    private String region;

    ActivityRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
}
