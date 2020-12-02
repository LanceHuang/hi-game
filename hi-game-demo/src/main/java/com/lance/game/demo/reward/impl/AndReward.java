package com.lance.game.demo.reward.impl;

import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.reward.AbstractReward;
import com.lance.game.demo.reward.IReward;

import java.util.LinkedList;
import java.util.List;

/**
 * 合并奖励
 *
 * @author Lance
 * @since 2020/12/1
 */
public class AndReward extends AbstractReward {

    /** 奖励集合 */
    private final List<IReward> rewards = new LinkedList<>();

    @Override
    public void parse(String value) {
    }

    @Override
    public void reward(Player player) {
        for (IReward reward : rewards) {
            reward.reward(player);
        }
    }

    public void addReward(IReward reward) {
        if (reward == null) {
            return;
        }
        this.rewards.add(reward);
    }
}
