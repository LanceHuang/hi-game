package com.lance.game.demo.consume;

import com.lance.game.demo.util.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

/**
 * 道具消耗
 *
 * @author Lance
 */
public class ItemConsume extends AbstractConsume {

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
    public void doVerify(Player player, VerifyResult verifyResult) {
        // todo 背包校验
    }

    @Override
    public void consume(Player player) {
        // todo 扣除道具
    }
}
