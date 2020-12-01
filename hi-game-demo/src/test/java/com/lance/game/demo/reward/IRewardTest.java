package com.lance.game.demo.reward;

import com.lance.game.demo.module.player.model.Player;
import org.junit.jupiter.api.Test;

/**
 * @author Lance
 * @since 2020/12/1
 */
class IRewardTest {

    @Test
    void test() {
        RewardDef rewardDef1 = new RewardDef();
        rewardDef1.setType(RewardType.ITEM);
        rewardDef1.setValue("10086:1");
        RewardDef rewardDef2 = new RewardDef();
        rewardDef2.setType(RewardType.ITEM);
        rewardDef2.setValue("10087:1");
        IReward reward = RewardUtils.parseReward(new RewardDef[]{rewardDef1, rewardDef2});

        Player mockPlayer = new Player();
        mockPlayer.setLevel(15);
        reward.reward(mockPlayer);
    }
}