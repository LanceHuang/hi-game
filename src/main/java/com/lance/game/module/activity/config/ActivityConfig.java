package com.lance.game.module.activity.config;

import com.lance.config.annotation.GameConfig;
import lombok.Data;

/**
 * 活动配置
 *
 * @author Lance
 * @since 2020/1/14 14:31
 */
@Data
@GameConfig
public class ActivityConfig {

    /** 活动id */
    private int    id;
    /** 活动类型 */
    private int    type;
    /** 活动名称 */
    private String name;
    /** 活动时间 */
    private String timeConfig;
    /** 活动奖励 */
    private int    rewardId;

}
