package com.lance.game.demo.reward;

import com.lance.game.demo.reward.impl.AndReward;

/**
 * 奖励工具
 *
 * @author Lance
 * @since 2020/12/1
 */
public class RewardUtils {

    /** 空奖励 */
    private static final IReward trueReward = RewardType.TRUE.create();

    /**
     * 解析奖励定义
     */
    public static IReward parseReward(RewardDef def) {
        if (def == null || def.getType() == null) {
            return trueReward;
        }

        AbstractReward newReward = (AbstractReward) def.getType().create();
        newReward.parse(def.getValue());
        return newReward;
    }

    /**
     * 解析奖励定义
     */
    public static IReward parseReward(RewardDef[] defs) {
        if (defs == null || defs.length == 0) {
            return trueReward;
        }

        // 减少小对象
        if (defs.length == 1) {
            return parseReward(defs[0]);
        }

        AndReward andReward = (AndReward) RewardType.AND.create();
        for (RewardDef def : defs) {
            andReward.addReward(parseReward(def));
        }
        return andReward;
    }
}
