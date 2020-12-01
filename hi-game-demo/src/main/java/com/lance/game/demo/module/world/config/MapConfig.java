package com.lance.game.demo.module.world.config;

import com.lance.game.demo.condition.ConditionUtils;
import com.lance.game.demo.condition.ICondition;
import com.lance.game.demo.consume.ConsumeUtils;
import com.lance.game.demo.consume.IConsume;
import com.lance.game.demo.condition.ConditionDef;
import com.lance.game.demo.consume.ConsumeDef;
import lombok.Data;

/**
 * 地图配置
 *
 * @author Lance
 */
@Data
public class MapConfig {

    /** 地图id */
    private int mapId;
    /** 初始频道数（新手村） */
    private int initChannelNum;
    /** 最大频道数 */
    private int maxChannelNum;
    /** 最大玩家数（单人场景） */
    private int maxPlayerNum;
    /** 增线玩家数（一般都会小于maxPlayerNum，这样能让玩家体验更流畅） */
    private int addPlayerNum;

    /** 进图条件 */
    private ConditionDef[] conditionDefs;
    private ICondition condition;

    /** 进图消耗 */
    private ConsumeDef[] consumeDefs;
    private IConsume consume;

    public ICondition getCondition() {
        if (this.condition == null) {
            this.condition = ConditionUtils.parseCondition(this.conditionDefs);
        }
        return this.condition;
    }

    public IConsume getConsume() {
        if (this.consume == null) {
            this.consume = ConsumeUtils.parseConsume(this.consumeDefs);
        }
        return this.consume;
    }
}
