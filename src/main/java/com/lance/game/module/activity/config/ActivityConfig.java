package com.lance.game.module.activity.config;

import lombok.Data;

/**
 * 活动配置
 *
 * @author Lance
 * @since 2020/1/14 14:31
 */
@Data
public class ActivityConfig {

    /** 活动id */
    private int    id;
    /** 活动类型 */
    private int    type;
    /** 活动名称 */
    private String name;
    /** 开启时间 */
    private String startTime;
    /** 结束时间 */
    private String stopTime;

}
