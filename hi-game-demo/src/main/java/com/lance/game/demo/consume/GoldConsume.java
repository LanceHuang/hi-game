package com.lance.game.demo.consume;

import com.lance.game.demo.util.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

/**
 * 钻石消耗
 *
 * @author Lance
 */
public class GoldConsume extends AbstractConsume {

    private long value;

    @Override
    public void parse(String value) {
        this.value = Integer.parseInt(value);
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