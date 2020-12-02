package com.lance.game.demo.reward.impl;

import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.reward.AbstractReward;

/**
 * 道具奖励
 *
 * @author Lance
 * @since 2020/12/1
 */
public class ItemReward extends AbstractReward {

    /** 道具id */
    private int itemId;
    /** 道具数量 */
    private int itemNum;

    @Override
    public void parse(String value) {
        String[] split = value.split(":");
        this.itemId = Integer.parseInt(split[0]);
        this.itemNum = Integer.parseInt(split[1]);
    }

    @Override
    public void reward(Player player) {
        // todo
    }
}
