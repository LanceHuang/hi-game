package com.lance.game.demo.reward;

import lombok.Getter;
import lombok.Setter;

/**
 * 奖励定义
 *
 * @author Lance
 * @since 2020/12/1
 */
@Getter
@Setter
public class RewardDef {

    /** 奖励类型 */
    private RewardType type;

    /** 奖励值 */
    private String value;
}
