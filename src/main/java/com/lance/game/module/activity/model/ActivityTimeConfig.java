package com.lance.game.module.activity.model;

import com.lance.game.module.activity.constant.ActivityRegion;
import lombok.Data;

/**
 * 活动时间配置
 *
 * @author Lance
 * @since 2020/2/24 12:43
 */
@Data
public class ActivityTimeConfig {

    private ActivityRegion region;

    private String value;

}
