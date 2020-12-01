package com.lance.game.demo.reward;

import com.lance.game.demo.reward.impl.AndReward;
import com.lance.game.demo.reward.impl.ItemReward;

/**
 * 奖励类型
 *
 * @author Lance
 * @since 2020/12/1
 */
public enum RewardType {

    /** 合并奖励 */
    AND(AndReward.class),
    /** 道具奖励 */
    ITEM(ItemReward.class);

    private final Class<? extends IReward> clazz;

    RewardType(Class<? extends IReward> clazz) {
        this.clazz = clazz;
    }

    /**
     * 创建奖励
     */
    public IReward create() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("创建奖励失败：" + this.name());
    }
}
