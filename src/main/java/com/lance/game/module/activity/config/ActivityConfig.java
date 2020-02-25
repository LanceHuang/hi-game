package com.lance.game.module.activity.config;

import com.lance.config.annotation.Config;
import lombok.Data;

/**
 * 活动配置
 *
 * @author Lance
 * @since 2020/1/14 14:31
 */
@Data
@Config
public class ActivityConfig {

    /** 活动id */
    private int    id;
    /** 活动类型 */
    private int    type;
    /** 活动名称 */
    private String name;
    /** 活动时间 */
    private String timeConfig; // todo 兼容不同渠道的开服时间（国际服和国服）
    /** 活动奖励 */
    private int    rewardId; // todo 兼容多种条件及奖励（譬如，AFK的N档玫瑰花兑换）


}
