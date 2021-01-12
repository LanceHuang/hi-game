package com.lance.game.demo.core.reward;

import com.lance.game.demo.module.player.model.Player;

/**
 * 奖励
 *
 * @author Lance
 * @since 2020/12/1
 */
public interface IReward {

    /**
     * 发奖
     */
    void reward(Player player);
}
